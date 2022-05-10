package ru.itis.anifox.util.email;

import freemarker.template.Configuration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class FreemarkerEmailSender implements EmailSender{

    private final JavaMailSender mailSender;

    private final Configuration freemarkerConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendMessage(InfoMail info) {
        Runnable runnable = () -> {
            MimeMessagePreparator preparator = mimeMessage -> prepare(mimeMessage, info);
            mailSender.send(preparator);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void prepare(MimeMessage message, InfoMail info) throws Exception {
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setSubject(info.getSubject());
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                freemarkerConfiguration.getTemplate(info.getTemplateName()+".ftlh", "UTF-8"), info.getData()
        );
        messageHelper.setText(text, true);
        messageHelper.setTo(info.getTo());
        messageHelper.setFrom(from);
    }

}
