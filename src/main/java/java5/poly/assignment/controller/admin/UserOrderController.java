package java5.poly.assignment.controller.admin;

import jakarta.servlet.ServletContext;
import java5.poly.assignment.model.OrderDetail;
import java5.poly.assignment.model.Product;
import java5.poly.assignment.model.UserOrder;
import java5.poly.assignment.service.ServiceI.OrderDetailServiceI;
import java5.poly.assignment.service.ServiceI.ProductServiceI;
import java5.poly.assignment.service.ServiceI.UserOrderServiceI;
import java5.poly.assignment.ulities.CookieUlities;
import java5.poly.assignment.ulities.ImageUlities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/userorder")
public class UserOrderController {
    private UserOrderServiceI service;
    private OrderDetailServiceI oderService;
    private ProductServiceI productService;
    private ServletContext context;
    private CookieUlities cookie;
    private ImageUlities imageUlities;

    @Autowired
    public UserOrderController(UserOrderServiceI service, ProductServiceI productService, ServletContext context,
                               CookieUlities cookie, ImageUlities imageUlities, OrderDetailServiceI oderService) {
        this.service = service;
        this.productService = productService;
        this.context = context;
        this.cookie = cookie;
        this.imageUlities = imageUlities;
        this.oderService = oderService;
    }

    @ModelAttribute("ordersnoconfirm")
    public List<UserOrder> getListOrderNoConfirm(){
        return service.getUserOrderByTrangThai(0);
    }

    @ModelAttribute("ordersconfirm")
    public List<UserOrder> getListOrderConfirm(){
        return service.getUserOrderByTrangThai(1);
    }

    @ModelAttribute("orderscomplete")
    public List<UserOrder> getListOrderComplete(){
        return service.getUserOrderByTrangThai(-1);
    }

    @ModelAttribute("userorder")
    public UserOrder getUserOrder(){
        return new UserOrder();
    }

    @ModelAttribute(name = "ImageUlities")
    public ImageUlities getImageUlities(){
        return imageUlities;
    }

    @ModelAttribute("nameobject")
    public String getNameObject(){
        return "Đơn đặt hàng";
    }


    @ModelAttribute("linkobject")
    public String getLinkObject(){
        return "/admin/userorder";
    }

    @GetMapping("/data")
    public String index(Model model){
        model.addAttribute("viewadmin", "order/framedataorder.jsp");
        model.addAttribute("orderurl", "/admin/userorder/create");
        return "admin/index";
    }

    @GetMapping("/order")
    public String order(Model model){
        model.addAttribute("viewadmin", "order/order.jsp");
        model.addAttribute("orderurl", "/admin/userorder/create");
        return "admin/index";
    }

    @GetMapping("/create")
    public String adminAdd(Model model){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("today", sdf.format(date));
        model.addAttribute("products", productService.getList());
        model.addAttribute("viewadmin", "order/formcreate.jsp");
        model.addAttribute("functionname", "Create");
        return "admin/index";
    }

    @PostMapping("/create")
    public String add(@RequestParam("productorders")List<UUID> productOrders, @ModelAttribute("userorder")UserOrder userOrder){
        userOrder.setTrangThai(1);
        UserOrder userOrder1 = service.save(userOrder);
        for (UUID id: productOrders) {
            Product product = productService.getOne(id);
            OrderDetail orderDetail = new OrderDetail(null, product, userOrder1, Double.parseDouble(product.getGia()+""), 1);
            oderService.save(orderDetail);
        }
        return "redirect:/admin/userorder/update?id="+userOrder1.getID();
    }

    @GetMapping("/detail")
    public String adminDetail(Model model, @RequestParam("id") UUID id){
        UserOrder userOrder = service.getOne(id);
        List<OrderDetail> orderDetails = oderService.getOrderDetailByUserOrder(userOrder);
        model.addAttribute("userorder", userOrder);
        model.addAttribute("orderdetails", orderDetails);
        model.addAttribute("viewadmin", "order/orderreview.jsp");
        model.addAttribute("functionname", "Update");
        return "admin/index";
    }

    @GetMapping("/update")
    public String updateView(Model model, @RequestParam("id") UUID id) throws ParseException {
        UserOrder userOrder = service.getOne(id);
        List<OrderDetail> orderDetails = oderService.getOrderDetailByUserOrder(userOrder);
        List<Product> products = productService.getList();
        for (OrderDetail orderDetail: orderDetails){
            products.remove(orderDetail.getProduct());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(userOrder.getNgayTao().getYear()+"-"+
                userOrder.getNgayTao().getMonthValue()+"-"+userOrder.getNgayTao().getDayOfMonth());
        model.addAttribute("products", products);
        model.addAttribute("userorder", userOrder);
        model.addAttribute("ngayTao", sdf.format(date));
        model.addAttribute("orderdetails", orderDetails);
        model.addAttribute("viewadmin", "order/formupdate.jsp");
        return "admin/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("userorder")UserOrder userOrder, @RequestParam("ngayTao")LocalDate ngayTao
                        , @RequestParam(name = "productorders", defaultValue = "")List<UUID> productOrders){
        UserOrder check = service.getOne(userOrder.getID());
        if(check.getAccount()==null){
            userOrder.setTrangThai(-1);
        }else{
            userOrder.setTrangThai(1);
        }
        userOrder.setAccount(check.getAccount());
        userOrder.setNgayTao(ngayTao);
        if(!productOrders.isEmpty()){
            for (UUID idpro: productOrders) {
                Product product = productService.getOne(idpro);
                OrderDetail orderDetail = new OrderDetail(null, product, userOrder, Double.parseDouble(product.getGia()+""), 1);
                oderService.save(orderDetail);
            }
        }
        service.update(userOrder);
        return "redirect:/admin/userorder/data";
    }

    @PostMapping("/updateproduct/{id}")
    public String updateProduct(@PathVariable("id")UUID id, @RequestParam("quantity")int quantity){
        OrderDetail orderDetail = oderService.getOne(id);
        orderDetail.setSoluong(quantity);
        oderService.save(orderDetail);
        return "redirect:/admin/userorder/update?id="+orderDetail.getUserOrder().getID();
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("id")UUID id){
        UserOrder userOrder = service.getOne(id);
        userOrder.setTrangThai(1);
        service.save(userOrder);
        return "redirect:/admin/userorder/update?id="+id;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id")UUID id){
        UserOrder userOrder = service.getOne(id);
        List<OrderDetail> orderDetails = oderService.getOrderDetailByUserOrder(userOrder);
        for (int i = 0; i < orderDetails.size(); i++) {
            oderService.delete(orderDetails.get(i));
        }
        service.delete(userOrder);
        return "redirect:/admin/userorder/data";
    }

}
