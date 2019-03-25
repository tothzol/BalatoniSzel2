package hu.unipannon.mik.balatoniszel.core;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    // private TemplateEngine templateEngine; // Hogy lehet elérni a Template enginet?

    private void sendMail(String from, String to, String subject, String templateName, Map<String, String> parameters) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(from);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
        };

        try {
            this.mailSender.send(messagePreparator);
        } catch (MailException e) {}
    }
}
