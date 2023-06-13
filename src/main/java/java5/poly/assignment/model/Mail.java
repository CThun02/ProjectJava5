package java5.poly.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail {
    private String from="ADMIN";
    private String to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String body;
    public Mail(String to, String subject, String body) {
        this.from = "ADMIN";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
