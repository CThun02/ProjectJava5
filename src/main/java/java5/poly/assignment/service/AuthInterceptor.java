package java5.poly.assignment.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java5.poly.assignment.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Account user = (Account) session.getAttribute("account"); // lấy từ session
        String error = "";
        if(user == null) {
            error = "Please login!";
        }
        else if(!user.getADM() && uri.startsWith("/admin/")) {
            error = "Access denied!";
        }
        if(error.length() > 0) { // có lỗi
            session.setAttribute("security-uri", uri);
            response.sendRedirect("/account/login?error=" + error);
            return false;
        }
        return true;
    }
}
