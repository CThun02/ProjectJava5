package java5.poly.assignment.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageErrorsConfig {
    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasenames("classpath:message_errors/product", "classpath:message_errors/account");
        ms.setDefaultEncoding("utf-8");
        return ms;
    }
}
