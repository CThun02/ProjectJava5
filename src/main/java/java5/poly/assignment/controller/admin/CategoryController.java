package java5.poly.assignment.controller.admin;

import jakarta.validation.Valid;
import java5.poly.assignment.model.Category;
import java5.poly.assignment.service.ServiceImpl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @ModelAttribute("categories")
    public List<Category> getList(){
        return service.getList();
    }

    @ModelAttribute("category")
    public Category getCategory(){
        return new Category();
    }

    @ModelAttribute("nameobject")
    public String getNameObject(){
        return "Loại Sản Phẩm";
    }

    @ModelAttribute("linkobject")
    public String getLinkObject(){
        return "/admin/category";
    }

    @GetMapping("/data")
    public String data(Model model){
        model.addAttribute("viewadmin", "category/framedatacategory.jsp");
        return "admin/index";
    }

    @GetMapping("/create")
    public String adminAdd(Model model){
        model.addAttribute("viewadmin", "category/formcategory.jsp");
        model.addAttribute("functionname", "Create");
        return "admin/index";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("category") Category category){
        service.save(category);
        return "redirect:/admin/category/data";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") UUID id, Model model){
        model.addAttribute("viewadmin", "category/formcategory.jsp");
        model.addAttribute("functionname", "Update");
        model.addAttribute("cateurl", "/admin/category/update?id="+id);
        Category category = service.getOne(id);
        model.addAttribute("category", category);
        return "admin/index";
    }

    @PostMapping("/update")
    public String updateDB(@RequestParam("id")UUID id, Model model, @ModelAttribute("category")Category categoryUpdate){
        Category category = service.getOne(id);
        categoryUpdate.setID(category.getID());
        service.save(categoryUpdate);
        model.addAttribute("category", category);
        return "redirect:/admin/category/data";
    }

    @GetMapping("/delete")
    public String update(@RequestParam("id")UUID id){
        Category category = service.getOne(id);
        service.delete(category);
        return "redirect:/admin/category/data";
    }
}
