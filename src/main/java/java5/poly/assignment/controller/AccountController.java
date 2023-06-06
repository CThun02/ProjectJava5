package java5.poly.assignment.controller;

import jakarta.servlet.ServletContext;
import java5.poly.assignment.model.Account;
import java5.poly.assignment.service.ServiceI.AccountServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/account")
public class AccountController {
    private ServletContext context;
    private AccountServiceI service;

    @Autowired
    public AccountController(ServletContext context, AccountServiceI service) {
        this.context = context;
        this.service = service;
    }

    @ModelAttribute("account")
    public Account getAccount(){
        return new Account();
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login/login";
    }

    @GetMapping("/signin")
    public String signinForm(){
        return "login/sign";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute("account") Account account, @RequestParam() MultipartFile fileUpload){
        if (!fileUpload.isEmpty()) {
            try {
                String fileName = fileUpload.getOriginalFilename();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyymmddhhmmss");
                LocalDateTime now = LocalDateTime.now();
                String datenow =  dtf.format(now).toLowerCase();
                File file = new File(context.getRealPath("file/img/account"+File.separator+datenow+"_"+fileName));
                if(!file.exists()){
                    file.mkdirs();
                }
                fileUpload.transferTo(file);
                account.setPhoto("file/img/account"+File.separator+datenow+"_"+fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        account.setActivated(false);
        account.setADM(false);
        service.save(account);
        return "redirect:/account/login";
    }
}
