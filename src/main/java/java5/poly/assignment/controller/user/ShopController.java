package java5.poly.assignment.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java5.poly.assignment.model.Category;
import java5.poly.assignment.model.Product;
import java5.poly.assignment.service.ServiceI.CategoryServicel;
import java5.poly.assignment.service.ServiceI.ProductServiceI;
import java5.poly.assignment.ulities.CookieUlities;
import java5.poly.assignment.ulities.ImageUlities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@RequestMapping("/shop")
@Controller
public class ShopController {
    private ProductServiceI service;
    private CategoryServicel serviceCate;
    private ImageUlities imgUlities;
    private CookieUlities cookieUlities;
    private HttpServletResponse res;
    private HttpServletRequest req;
    private int pageTotal=0;
    private int pageNumber=0;
    private int indexLocation = 0;

    @Autowired
    public ShopController(ProductServiceI service, CategoryServicel serviceCate, ImageUlities imgUlities,
                          CookieUlities cookieUlities, HttpServletResponse res, HttpServletRequest req) {
        this.service = service;
        this.serviceCate = serviceCate;
        this.imgUlities = imgUlities;
        this.cookieUlities = cookieUlities;
        this.res = res;
        this.req = req;
    }



    @ModelAttribute("imgUlities")
    public ImageUlities setImageUlities(){
        return imgUlities;
    }

    @ModelAttribute("categories")
    public List<Category> getCategories(){
        return serviceCate.getList();
    }

    @GetMapping("")
    public String shopView(Model model, @RequestParam(name = "sort",defaultValue = "gia,asc")String sort){
        String[] sortPram = sort.split(",");
        String sortName = sortPram[0];
        Sort.Direction sortDirection;
        if(sortPram[1].equals("asc")){
            indexLocation=0;
            sortDirection = Sort.Direction.ASC;
        }else{
            indexLocation = 1;
            sortDirection = Sort.Direction.DESC;
        }
        Sort sortA = Sort.by(sortDirection, sortName);
        List<Product> products;
        Page<Product> productData = service.getProducts(pageNumber, 6, sortA);
        pageTotal = productData.getTotalPages();
        products = productData.getContent();
        model.addAttribute("pagenumber", pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("viewusers", "shop/shop.jsp");
        return "user/index";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam("idcate")String idcate, Model model){
        cookieUlities.create("idcate", idcate, res);
        Category category = serviceCate.getOne(UUID.fromString(idcate));
        pageNumber=0;
        Page<Product> productData = service.findProductsByCategory(category, pageNumber, 6);
        pageTotal = productData.getTotalPages();
        List<Product> products = productData.getContent();
        indexLocation = 2;
        model.addAttribute("pagenumber", pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("viewusers", "shop/shop.jsp");
        return "user/index";
    }

    @GetMapping("/searchbyname")
    public String searchByName(@RequestParam("namepro") String namepro, Model model){
        cookieUlities.create("nameSearch", namepro, res);
        pageNumber=0;
        Page<Product> productPage = service.getProductsbyName(namepro, pageNumber, 6);
        pageTotal = productPage.getTotalPages();
        List<Product>products = productPage.getContent();
        model.addAttribute("products", products);
        indexLocation=3;
        model.addAttribute("pagenumber", pageNumber);
        model.addAttribute("products", products);
        model.addAttribute("viewusers", "shop/shop.jsp");
        return "user/index";
    }

    @GetMapping("/productdetail")
    public String detail(Model model, @RequestParam("id")UUID id){
        Product product = service.getOne(id);
        model.addAttribute("product", product);
        model.addAttribute("viewusers", "shop/productdetail.jsp");
        return "user/index";
    }

    @GetMapping("/addtocart")
    public String addToCart(@RequestParam("id") UUID id, @RequestParam(name = "quantity", defaultValue = "1")int quantity, Model model){
        Product product = service.getOne(id);
        if(quantity>product.getSoLuongTon() || quantity<=0){
            model.addAttribute("ErrorQuantity", "Vui lòng nhập số lượng lớn hơn 0 và bé hơn "+product.getSoLuongTon());
            model.addAttribute("product", product);
            model.addAttribute("viewusers", "shop/productdetail.jsp");
            return "user/index";
        }
        Double price = Double.valueOf(product.getGia()+"");
        String cartItem = "id:"+id+"price:"+price+"quantity:"+quantity;
        cookieUlities.create("cartitem" + product.getTen(), cartItem, res);
        return "redirect:/shop";
    }

    @GetMapping("/previous")
    public String previous(){
        String href = "redirect:/shop";
        pageNumber--;
        if(pageNumber<0){
            pageNumber = pageTotal-1<0?0:pageTotal-1;
        }
        switch (indexLocation){
            case 1:
                href+="?sort=gia,desc";
                break;
            case 2:
                href+=("/filter?idcate="+cookieUlities.getCookie("idcate").getValue());
                break;
            case 3:
                href+=("/searchbyname?namepro="+cookieUlities.getCookie("nameSearch").getValue());
                break;
        }
        return href;
    }

    @GetMapping("/next")
    public String next(){
        String href = "redirect:/shop";
        pageNumber++;
        if(pageNumber==pageTotal){
            pageNumber = 0;
        }
        switch (indexLocation){
            case 1:
                href+="?sort=gia,desc";
                break;
            case 2:
                href+=("/filter?idcate="+cookieUlities.getCookie("idcate").getValue());
                break;
            case 3:
                href+=("/searchbyname?namepro="+cookieUlities.getCookie("nameSearch").getValue());
                break;
        }
        return href;
    }

}
