package java5.poly.assignment.controller.admin;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java5.poly.assignment.model.Category;
import java5.poly.assignment.model.Product;
import java5.poly.assignment.service.ServiceI.CategoryServicel;
import java5.poly.assignment.service.ServiceI.ProductServiceI;
import java5.poly.assignment.service.ServiceImpl.CategoryService;
import java5.poly.assignment.service.ServiceImpl.ProductService;
import java5.poly.assignment.ulities.CookieUlities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.net.HttpCookie;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private ProductServiceI service;
    private CategoryServicel servceCate;
    private ServletContext context;
    private CookieUlities cookie;
    private int pageNumber=0;
    private int pageTotal;
    private int indexLocation;

    @Autowired
    public ProductController(ProductService service, CategoryService servceCate, ServletContext context, CookieUlities cookie) {
        this.service = service;
        this.servceCate = servceCate;
        this.context = context;
        this.cookie = cookie;
    }


    @ModelAttribute(name = "product")
    public Product getProduct(){
        return new Product();
    }

    @ModelAttribute(name = "categories")
    public List<Category> getListCate(){
        return servceCate.getList();
    }

    @ModelAttribute("nameobject")
    public String getNameObject(){
        return "Sản Phẩm";
    }


    @ModelAttribute("linkobject")
    public String getLinkObject(){
        return "/admin/product";
    }

    @GetMapping("/data")
    public String index(Model model, @RequestParam(value = "pagenumber", defaultValue = "0") int pageNumber){
        List<Product> products;
        Page<Product> productData = service.getProducts(pageNumber, 5);
        pageTotal = productData.getTotalPages();
        products = productData.getContent();
        indexLocation = 0;
        model.addAttribute("viewadmin", "product/framedataproduct.jsp");
        model.addAttribute("url", "/admin/product/create");
        model.addAttribute("pagenumber", pageNumber);
        model.addAttribute("products", products);
        return "admin/index";
    }

    @GetMapping("/create")
    public String adminAdd(Model model){
        model.addAttribute("viewadmin", "product/formproduct.jsp");
        model.addAttribute("functionname", "Create");
        return "admin/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product product, @RequestParam("imgUpload") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
                LocalDateTime now = LocalDateTime.now();
                String datenow =  dtf.format(now).toLowerCase();
                File fileUpload = new File(context.getRealPath("file/img/product"+File.separator+datenow+"_"+fileName));
                if(!fileUpload.exists()){
                    fileUpload.mkdirs();
                }
                file.transferTo(fileUpload);
                product.setImg(context.getRealPath("file/img/product"+File.separator+datenow+"_"+fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.save(product);
        return "redirect:/admin/product/data";
    }

    @GetMapping("/update")
    public String adminUpdate(Model model, @RequestParam("id") UUID id){
        Product product = service.getOne(id);
        model.addAttribute("viewadmin", "product/formproduct.jsp");
        model.addAttribute("functionname", "Update");
        model.addAttribute("url", "/admin/product/update?id=" + product.getID());
        model.addAttribute("functionname", "Update");
        model.addAttribute("product", product);
        return "admin/index";
    }

    @PostMapping("/update")
    public String updateDB(@ModelAttribute("product") Product product, @RequestParam("id") UUID id, @RequestParam("imgUpload")MultipartFile file) throws ParseException {
        Product productOld = service.getOne(id);
        product.setID(id);
        if (!file.isEmpty()) {
            try {
                String nameFileDelete = productOld.getImg();
                String fileName = file.getOriginalFilename();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
                LocalDateTime now = LocalDateTime.now();
                String datenow =  dtf.format(now).toLowerCase();
                File fileUpload = new File(context.getRealPath("file/img/product"+File.separator+datenow+"_"+fileName));
                if(!fileUpload.exists()){
                    fileUpload.mkdirs();
                }
                file.transferTo(fileUpload);
                product.setImg(context.getRealPath("file/img/product"+File.separator+datenow+"_"+fileName));
                service.save(product);
                File fileDelete = new File(nameFileDelete);
                fileDelete.delete();
                return "redirect:/admin/product/data";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        product.setImg(productOld.getImg());
        service.save(product);
        return "redirect:/admin/product/data";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") UUID id) throws ParseException {
        Product product = service.getOne(id);
        String nameFileDelete = product.getImg();
        service.delete(product);
        File fileDelete = new File(nameFileDelete);
        fileDelete.delete();
        return "redirect:/admin/product/data";
    }

    @GetMapping("/searchbyprice")
    public String searchByName(@RequestParam("pricemin") double priceMin, @RequestParam("pricemax") double pricemax,
                               Model model, HttpServletResponse res){
        cookie.create("priceSearchMin", String.valueOf(priceMin), res);
        cookie.create("priceSearchMax", String.valueOf(pricemax), res);
        Page<Product> productPage = service.getProductsbyPrice(priceMin, pricemax, pageNumber, 5);
        pageTotal = productPage.getTotalPages();
        pageNumber = pageNumber>=pageTotal?0:pageNumber;
        List<Product>products = productPage.getContent();
        model.addAttribute("products", products);
        indexLocation=1;
        model.addAttribute("viewadmin", "product/framedataproduct.jsp");
        model.addAttribute("url", "/admin/product/create");
        model.addAttribute("pagenumber", pageNumber);
        return "admin/index";
    }
    @GetMapping("/searchbyname")
    public String searchByName(@RequestParam("namepro") String namepro, Model model, HttpServletResponse res){
        cookie.create("nameSearch", namepro, res);
        Page<Product> productPage = service.getProductsbyName(namepro, pageNumber, 5);
        pageTotal = productPage.getTotalPages();
        pageNumber = pageNumber>=pageTotal?0:pageNumber;
        List<Product>products = productPage.getContent();
        model.addAttribute("products", products);
        indexLocation=2;
        model.addAttribute("viewadmin", "product/framedataproduct.jsp");
        model.addAttribute("url", "/admin/product/create");
        model.addAttribute("pagenumber", pageNumber);
        return "admin/index";
    }

    @GetMapping("/previous")
    public String previous(Model model, RedirectAttributes redirectAttributes, HttpServletRequest req){
        pageNumber--;
        if(pageNumber<0){
            pageNumber = pageTotal-1<=0?0:pageTotal-1;
        }
        if(indexLocation==1){
            return "redirect:/admin/product/searchbyprice?pricemin="+cookie.getCookies("priceSearchMin", req).getValue()
                    +"&pricemax="+cookie.getCookies("priceSearchMax", req).getValue()+"&pagenumber="+pageNumber;
        }else if(indexLocation==2){
            return "redirect:/admin/product/searchbyname?namepro="+cookie.getCookies("nameSearch",req).getValue()+"&pagenumber="+pageNumber;
        }else{
            return "redirect:/admin/product/data?pagenumber="+pageNumber;
        }
    }

    @GetMapping("/next")
    public String next(Model model, HttpServletRequest req){
        pageNumber++;
        if(pageNumber==pageTotal){
            pageNumber=0;
        }
        if(indexLocation==1){
            return "redirect:/admin/product/searchbyprice?pricemin="+cookie.getCookies("priceSearchMin", req).getValue()
                    +"&pricemax="+cookie.getCookies("priceSearchMax", req).getValue()+"&pagenumber="+pageNumber;
        }else if(indexLocation==2){
            return "redirect:/admin/product/searchbyname?namepro="+cookie.getCookies("nameSearch",req).getValue()+"&pagenumber="+pageNumber;
        }else if(indexLocation==3){
            return "redirect:/admin/product/filter?idcate="+cookie.getCookies("idcatefilter",req).getValue()+"&pagenumber="+pageNumber;
        }else{
            return "redirect:/admin/product/data?pagenumber="+pageNumber;
        }
    }

    @GetMapping("/filter")
    public String filter(@RequestParam("idcate")String idcate, Model model, HttpServletResponse res){
        cookie.create("idcatefilter", idcate, res);
        Category category = servceCate.getOne(UUID.fromString(idcate));
        Page<Product> productData = service.findProductsByCategory(category, pageNumber, 5);
        pageTotal = productData.getTotalPages();
        pageNumber = pageNumber>=pageTotal?0:pageNumber;
        List<Product> products = productData.getContent();
        indexLocation = 3;
        model.addAttribute("viewadmin", "product/framedataproduct.jsp");
        model.addAttribute("url", "/admin/product/create");
        model.addAttribute("pagenumber", pageNumber);
        model.addAttribute("products", products);
        return "admin/index";
    }
}
