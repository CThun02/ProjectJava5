package java5.poly.assignment.service.ServiceI;

import jakarta.mail.MessagingException;

public interface EmailServiceI {
    public void push(String to, String subject, String body) throws MessagingException;
}
