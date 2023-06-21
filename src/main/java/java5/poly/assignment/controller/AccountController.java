package java5.poly.assignment.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java5.poly.assignment.model.Account;
import java5.poly.assignment.service.ServiceI.AccountServiceI;
import java5.poly.assignment.service.ServiceI.EmailServiceI;
import java5.poly.assignment.ulities.CookieUlities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@SessionScope
@Controller
@RequestMapping("/account")
public class AccountController {
    private ServletContext context;
    private AccountServiceI service;
    private EmailServiceI emailService;
    private HttpSession session;
    private CookieUlities cookieUlities;

    @Autowired
    public AccountController(ServletContext context, AccountServiceI service, EmailServiceI emailService,
                             HttpSession session, CookieUlities cookieUlities) {
        this.context = context;
        this.service = service;
        this.emailService = emailService;
        this.session = session;
        this.cookieUlities = cookieUlities;
    }

    @ModelAttribute("account")
    public Account getAccount(){
        return new Account();
    }

    @GetMapping("/login")
    public String loginForm(){
        Cookie cookie = cookieUlities.getCookie("user");
        if(cookie!=null){
            cookieUlities.deleteCookieByNameNPath("user", "/");
        }
        return "login/login";
    }

    @PostMapping ("/login")
    public String login(@RequestParam("userName")String userName, @RequestParam("pass")String pass, Model model){
        Account account = service.getOne(userName);
        if(account==null){
            model.addAttribute("mess", "Sai tai khoan hoạc mat khau!");
            return "login/login";
        }else{
            session.setAttribute("account", account);
            if(account.getPass().equalsIgnoreCase(pass)){
                if(account.getADM()){
                    return "redirect:/admin/product/data";
                }else{
                    cookieUlities.createNSetPath("user", account.getUserName(), "/");
                    return "redirect:/";
                }
            }
            model.addAttribute("mess", "Sai tai khoan hoạc mat khau!");
            return "login/login";
        }
    }

    @GetMapping("/signin")
    public String signinForm(){
        return "login/sign";
    }

    @GetMapping("/forgot")
    public String forgotForm(){
        return "login/forget";
    }

    @PostMapping("/forgot")
    public String forgot(@RequestParam("email")String email, Model model){
        try {
            if(service.isAccountExist(email)){
                UUID otp = UUID.randomUUID();
                session.setAttribute("id", otp);
                session.setAttribute("user", service.getAccountByEmail(email));
                emailService.push(email, "Reset Your Password", "OTP: " + otp);
            }else{
                model.addAttribute("mess", "Email not registered with any account!");
                return "login/forget";
            }

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "login/importopt";
    }

    @PostMapping("/importotp")
    public String importOtp(@RequestParam("otp")String otp, Model model){
        if((session.getAttribute("id")+"").equalsIgnoreCase(otp)){
            session.removeAttribute("id");
            return "login/resetpassword";
        }
        model.addAttribute("mess", "OTP not correctly!");
        return "login/importopt";
    }

    @PostMapping("/resetpassword")
    public String importOtp(@RequestParam("newpassword")String newpassword){
        Account account = (Account) session.getAttribute("user");
        account.setPass(newpassword);
        service.update(account);
        session.removeAttribute("user");
        return "redirect:/account/login";
    }

    @PostMapping("/signin")
    public String signin(@ModelAttribute("account") @Valid Account account, BindingResult result,
                         @RequestParam() MultipartFile fileUpload){
        if(result.hasErrors()){
            return "login/sign";
        }
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
                account.setPhoto(context.getRealPath("file/img/account"+File.separator+datenow+"_"+fileName));
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
