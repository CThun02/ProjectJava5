package java5.poly.assignment.ulities;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpCookie;
import java.util.UUID;

@Component
public class CookieUlities {
    public void create(String name, String value,HttpServletResponse res){
        Cookie cookie = new Cookie(name, value);
        res.addCookie(cookie);
        cookie.setMaxAge(30*60);
    }

    public Cookie getCookies(String name, HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }

    public void deleteCookieByName(String name,HttpServletResponse res, HttpServletRequest req){
        Cookie cookie = getCookies(name, req);
        cookie.setMaxAge(0);
        cookie.setValue(null);
        res.addCookie(cookie);
    }

}
