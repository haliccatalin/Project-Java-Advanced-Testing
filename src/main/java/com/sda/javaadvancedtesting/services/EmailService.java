package com.sda.javaadvancedtesting.services;

import com.sda.javaadvancedtesting.model.EmailSender;

public class EmailService {
    private final EmailSender emailSender;

    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        // Business logic for sending an email
        emailSender.send(to, subject, body);
    }
}
