package org.example.odc.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.odc.service.SendMail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class JavaMailImpl implements SendMail {
    private final JavaMailSender javaMailSender;
    public JavaMailImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(String to, String subject, String content) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom("barhamadieng66@gmail.com", "School Spring");
            helper.setTo(to);

            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        }catch (MessagingException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}
