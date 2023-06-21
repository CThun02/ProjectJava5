package java5.poly.assignment.ulities;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;

@Component
public class CookieUlities {
    private HttpServletRequest req;
    private HttpServletResponse res;

    @Autowired
    public CookieUlities(HttpServletRequest req, HttpServletResponse res) {
        this.req = req;
        this.res = res;
    }

    public void create(String name, String value, HttpServletResponse res){
        createNSetPath(name, value, "/shop");
        createNSetPath(name, value, "/cart");

    }

    public void createNSetPath(String name, String value, String path){
        Cookie cookie = new Cookie(name, value);
        Cookie cookie1 = getCookie(name);
        if (cookie1!=null && cookie1.getValue().contains("cart")){
            int indexIdQuantityCookie = value.lastIndexOf(":");
            int quantity = getQuantityValue(cookie1.getValue()) + getQuantityValue(cookie.getValue());
            cookie.setValue(cookie.getValue().substring(0, indexIdQuantityCookie+1)+quantity);
        }
        cookie.setPath(path);
        cookie.setMaxAge(30*60);
        res.addCookie(cookie);
    }
    private int getQuantityValue(String value){
        int indexIdQuantityCookie = value.lastIndexOf(":");
        int quantity = Integer.parseInt(value.substring(indexIdQuantityCookie+1));
        return quantity;
    }

    public Cookie getCookie(String name){
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }

    public List<Cookie> getCookies(String name, HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        List<Cookie> cookiesGet = new ArrayList<>();
        for (Cookie cookie: cookies) {
            if(cookie.getName().contains(name)){
                cookiesGet.add(cookie);
            }
        }
        return cookiesGet.isEmpty()?null:cookiesGet;
    }

    public void deleteCookieByNameNPath(String name, String path){
        Cookie cookie = getCookie(name);
        cookie.setMaxAge(0);
        cookie.setValue(null);
        cookie.setPath(path);
        res.addCookie(cookie);
    }

}
