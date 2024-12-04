package org.fintech.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private final String from;

    public EmailService(JavaMailSender emailSender, @Value("${spring.mail.username}") String from) {
        this.emailSender = emailSender;
        this.from = from;
    }

    public void sendMessage(String to, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(text);
        emailSender.send(message);
    }
}
