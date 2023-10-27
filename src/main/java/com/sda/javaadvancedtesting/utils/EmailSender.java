package com.sda.javaadvancedtesting.utils;



public class EmailSender {
    public void send(String toEmail, String subject, String message) {
        System.out.println("Send method called!");
        System.out.println(toEmail);
        System.out.println(subject);
        System.out.println(message);
    }
}
