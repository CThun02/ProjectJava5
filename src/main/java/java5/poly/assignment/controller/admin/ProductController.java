package java5.poly.assignment.controller.admin;

import jakarta.servlet.ServletContext;
import java5.poly.assignment.model.Category;
import java5.poly.assignment.model.Product;
import java5.poly.assignment.service.ServiceImpl.CategoryService;
import java5.poly.assignment.service.ServiceImpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    private ProductService service;
    private CategoryService servceCate;
    private ServletContext context;
    private int pageNumber=0;
    private int pageTotal;

    @Autowired
    public ProductController(ProductService service, CategoryService servceCate, ServletContext context) {
        this.service = service;
        this.servceCate = servceCate;
        this.context = context;
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
    public String index(Model model, @RequestParam(value = "pagenumber", defaultValue = "0") int pageNumber, RedirectAttributes redirectAttributes){
        model.addAttribute("viewadmin", "product/framedataproduct.jsp");
        model.addAttribute("url", "/admin/product/create");
        model.addAttribute("pagenumber", pageNumber);
        List<Product> products = (List<Product>) redirectAttributes.getFlashAttributes().get(0);
        if(products==null){
            Page<Product> productData = service.getProducts(pageNumber, 5);
            pageTotal = productData.getTotalPages();
            products = productData.getContent();
        }
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
                File dir = new File("src/main/resources/static/assets/img/product");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
                LocalDateTime now = LocalDateTime.now();
                String datenow =  dtf.format(now).toLowerCase();
                File serverFile = new File(dir.getAbsolutePath() + File.separator+datenow+"_"+ fileName);
                file.transferTo(serverFile);
                product.setImg(datenow+"_"+fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.save(product);
        return "redirect:/admin/product/data";
    }

    @GetMapping("/update")
    public String adminUpdate(Model model, @RequestParam("id") UUID id){
        model.addAttribute("viewadmin", "product/formproduct.jsp");
        model.addAttribute("functionname", "Update");
        Product product = service.getOne(id);
        String src = "/assets/img/product/" + product.getImg();
        model.addAttribute("url", "/admin/product/update?id=" + product.getID());
        model.addAttribute("functionname", "Update");
        model.addAttribute("img", src);
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
                File fileDirectory = new File("src/main/resources/static/assets/img/product/");
                File fileDelete = new File((fileDirectory.getAbsolutePath()+File.separator+nameFileDelete));
                fileDelete.delete();
                String fileName = file.getOriginalFilename();
                if (!fileDirectory.exists()) {
                    fileDirectory.mkdirs();
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
                LocalDateTime now = LocalDateTime.now();
                String datenow =  dtf.format(now).toLowerCase();
                File serverFile = new File(fileDirectory.getAbsolutePath() + File.separator+datenow+"_"+ fileName);
                file.transferTo(serverFile);

                product.setImg(datenow+"_"+ fileName);
                service.save(product);
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
        File fileDirectory = new File("src/main/resources/static/assets/img/product/");
        File fileDelete = new File((fileDirectory.getAbsolutePath()+File.separator+nameFileDelete));
        fileDelete.delete();
        service.delete(product);
        return "redirect:/admin/product/data";
    }

    @GetMapping("/previous")
    public String previous(Model model){
        pageNumber--;
        if(pageNumber<0){
            pageNumber = pageTotal-1;
        }
        return "redirect:/admin/product/data?pagenumber="+pageNumber;
    }

    @GetMapping("/next")
    public String next(Model model){
        pageNumber++;
        if(pageNumber==pageTotal){
            pageNumber=0;
        }
        return "redirect:/admin/product/data?pagenumber="+pageNumber;
    }
}
