package RGR.photogallery.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender getMailSeder(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("rgrphotogallery@gmail.com");
        javaMailSender.setPassword("rgrphoto1234"); //создать

        Properties property = javaMailSender.getJavaMailProperties();

        property.put("mail.transport.protocol", "smtp");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.starttls.enable", "true");
        property.put("mail.smtp.ssl.enable", "true");
        property.put("mail.debug", "true");

        return javaMailSender;
    }

}
