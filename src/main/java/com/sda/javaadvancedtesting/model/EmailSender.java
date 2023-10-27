package com.sda.javaadvancedtesting.model;



public class EmailSender {
    private Long id;

    public void send(String to, String subject, String message) {
        System.out.println(to);
        System.out.println(subject);
        System.out.println(message);
    }
}
