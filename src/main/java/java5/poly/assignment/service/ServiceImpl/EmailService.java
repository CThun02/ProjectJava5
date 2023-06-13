package java5.poly.assignment.service.ServiceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java5.poly.assignment.model.Mail;
import java5.poly.assignment.service.ServiceI.EmailServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService implements EmailServiceI {

    @Autowired
    private JavaMailSender mailSender;
    private List<MimeMessage> queue = new ArrayList<>();

    private void send(MimeMessage message){
        mailSender.send(message);
    }
    public void push(String to, String subject, String body) throws MessagingException {
        Mail mail = new Mail(to, subject, body);
        this.push(mail);
    }

    private void push(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(mail.getFrom());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setReplyTo(mail.getFrom());
        helper.setText(mail.getBody());
        queue.add(message);
    }

    @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    public void run(){
        int success=0, error=0;
        while (!queue.isEmpty()){
            MimeMessage message = queue.remove(0);
            try{
                mailSender.send(message);
                success++;
            }catch (Exception e){
                error++;
                e.printStackTrace();
            }
            System.out.printf("Send:%d, error:%d\n", success, error);
        }
    }

}
