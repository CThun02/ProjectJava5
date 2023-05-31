package java5.poly.assignment.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @GetMapping("/data")
    public String adminIndex(Model model){
        model.addAttribute("view", "framedataobject.jsp");
        model.addAttribute("linkobject", "/admin/product");
        return "admin/index";
    }
    @GetMapping("/create")
    public String adminAdd(Model model){
        model.addAttribute("view", "frameaddobject.jsp");
        model.addAttribute("linkobject", "/admin/product");
        return "admin/index";
    }

    @ModelAttribute("nameobject")
    public String getNameObject(){
        return "Sản Phẩm";
    }
}
