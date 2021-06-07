package RGR.photogallery.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailClient {

    private static String MAIL_FROM_DEFAULT = "no-reply@photo.ru";

    @Autowired
    private TemplateEngine templateEngine;

    private JavaMailSender mailSender;

    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String build(String template, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);

        return templateEngine.process(template, context);
    }


    public void sendMail(String from, String to, String subject, String msg) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setSubject(subject);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setText(msg, true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    public void sendTestEmail(String email) {
        Map<String, Object> replaces = new HashMap<>();

        replaces.put("fullName", email);

        String content = this.build("mail/test-email", replaces);
        String subject = "RGR photogallery";

        sendMail(MAIL_FROM_DEFAULT, email, subject, content);
    }

}
