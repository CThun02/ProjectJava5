package java5.poly.assignment.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/shop")
public class ShopController {
    @GetMapping("/data")
    public String adminIndex(Model model){
        model.addAttribute("view", "framedataobject.jsp");
        return "admin/shop/index";
    }
    @GetMapping("/create")
    public String adminAdd(Model model){
        model.addAttribute("view", "frameaddobject.jsp");
        return "admin/shop/index";
    }

    @ModelAttribute("nameobject")
    public String getNameObject(){
        return "Cửa Hàng";
    }
}
