package java5.poly.assignment.controller.user;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java5.poly.assignment.model.*;
import java5.poly.assignment.service.ServiceI.AccountServiceI;
import java5.poly.assignment.service.ServiceI.OrderDetailServiceI;
import java5.poly.assignment.service.ServiceI.ProductServiceI;
import java5.poly.assignment.service.ServiceI.UserOrderServiceI;
import java5.poly.assignment.ulities.CookieUlities;
import java5.poly.assignment.ulities.ImageUlities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/cart")
@Controller
public class CartController {
    private CookieUlities cookieUlities;
    private HttpServletResponse res;
    private HttpServletRequest req;
    private UserOrderServiceI service;
    private OrderDetailServiceI orderDetailService;
    private ProductServiceI productService;
    private ImageUlities imgUlities;
    private AccountServiceI accountService;

    @Autowired
    public CartController(CookieUlities cookieUlities, HttpServletResponse res, HttpServletRequest req,
                          ProductServiceI productService, ImageUlities imgUlities, UserOrderServiceI service,
                          OrderDetailServiceI orderDetailService, AccountServiceI accountService) {
        this.cookieUlities = cookieUlities;
        this.res = res;
        this.req = req;
        this.productService = productService;
        this.imgUlities = imgUlities;
        this.service = service;
        this.orderDetailService = orderDetailService;
        this.accountService = accountService;
    }

    @ModelAttribute("orders")
    public List<OrderDetail> getOrders(){
        List<OrderDetail> list = new ArrayList<>();
        List<Cookie> cookies = cookieUlities.getCookies("cartitem", req);
        if(cookies!=null){
            for (int i = 0; i < cookies.size(); i++) {
                int indexIdproCookie = cookies.get(i).getValue().indexOf(":");
                int indexIdPriceCookie = cookies.get(i).getValue().indexOf(":", indexIdproCookie+1);
                int indexIdQuantityCookie = cookies.get(i).getValue().lastIndexOf(":");
                Double gia = Double.parseDouble(cookies.get(i).getValue().substring(indexIdPriceCookie+1, indexIdQuantityCookie-(("quantity").length())));
                int quantity = Integer.parseInt(cookies.get(i).getValue().substring(indexIdQuantityCookie+1));
                UUID idpro = UUID.fromString(cookies.get(i).getValue().substring(indexIdproCookie+1,indexIdPriceCookie-("price".length())));
                Product product = productService.getOne(idpro);
                OrderDetail orderDetail = new OrderDetail(null, product, null, gia, quantity);
                list.add(orderDetail);
            }
        }
        return list;
    }

    @ModelAttribute("userorder")
    public UserOrder getAccount(){
        return new UserOrder();
    }

    @ModelAttribute("imgUlities")
    public ImageUlities setImageUlities(){
        return imgUlities;
    }

    @GetMapping("")
    public String cartView(Model model){
        model.addAttribute("viewusers", "cart/index.jsp");
        return "user/index";
    }

    @PostMapping("/update/{ID}")
    public String update(@PathVariable("ID") String id, @RequestParam(name="quantityupdate", defaultValue = "1")int quantityupdate, Model model){
        Product product = productService.getOne(UUID.fromString(id));
        if(quantityupdate<=0 || quantityupdate>product.getSoLuongTon()){
            model.addAttribute("ErrorQuantity", "Vui lòng nhập số lượng lớn hơn 0 và bé hơn "+product.getSoLuongTon());
            model.addAttribute("viewusers", "cart/index.jsp");
            return "user/index";
        }
        List<Cookie> cookies = cookieUlities.getCookies("cartitem", req);
        if(cookies!=null){
            for (int i = 0; i < cookies.size(); i++) {
                if(cookies.get(i).getValue().contains(id)){
                    int indexIdQuantityCookie = cookies.get(i).getValue().lastIndexOf(":");
                    String value = cookies.get(i).getValue().substring(0, indexIdQuantityCookie+1)+quantityupdate;
                    cookies.get(i).setValue(value);
                    cookies.get(i).setPath("/cart");
                    res.addCookie(cookies.get(i));
                    return"redirect:/cart";
                }
            }
        }
        return"redirect:/cart";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id){
        List<Cookie> cookies = cookieUlities.getCookies("cartitem", req);
        if(cookies!=null){
            for (Cookie cookie: cookies) {
                if(cookie.getValue().contains(id)){
                    Cookie cookieshop = cookie;
                    cookieUlities.deleteCookieByNameNPath(cookie.getName(), "/cart");
                    cookieUlities.deleteCookieByNameNPath(cookieshop.getName(), "/shop");
                    return"redirect:/cart";
                }
            }
        }
        return"redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutView(Model model){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        model.addAttribute("today", sdf.format(today));
        model.addAttribute("viewusers", "cart/proceedcheckout.jsp");
        return "user/index";
    }

    @PostMapping("/payment")
    public String payment(@ModelAttribute("userorder")UserOrder userOrder, @ModelAttribute("orders")List<OrderDetail> orderDetails){
        //Mua hàng không cần đăng nhập
        Cookie cookie = cookieUlities.getCookie("user");
        if(cookie!=null){
            userOrder.setAccount(accountService.getOne(cookie.getValue()));
        }
        userOrder.setTrangThai(0);
        UserOrder userOrder1 = service.save(userOrder);
        for (OrderDetail orderDetail:orderDetails) {
            orderDetail.setUserOrder(userOrder1);
            OrderDetail orderDetail1 = orderDetailService.save(orderDetail);
            Product product = productService.getOne(orderDetail1.getProduct().getID());
            product.setSoLuongTon(product.getSoLuongTon()-orderDetail.getSoluong());
            productService.update(product);
        }
        return "";
    }
}
