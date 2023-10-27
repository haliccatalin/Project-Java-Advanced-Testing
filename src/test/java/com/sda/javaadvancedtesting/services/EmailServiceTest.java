package com.sda.javaadvancedtesting.services;

import com.sda.javaadvancedtesting.utils.EmailSender;
import com.sda.javaadvancedtesting.services.EmailService;
import com.sda.javaadvancedtesting.utils.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class EmailServiceTest {
    @Test
    void testSendEmail() {
        // Create a mock EmailSender
        EmailSender emailSenderMock = mock(EmailSender.class);

        // Create the EmailService with the mock EmailSender
        EmailService emailService = new EmailService(emailSenderMock);

        // Call the method to be tested
        emailService.sendEmail("test@example.com", "Subject", "Body");

        // Verify that the EmailSender.send method was called
        verify(emailSenderMock, times(1)).send(anyString(), anyString(), anyString());
    }

    @Test
    public void testValidEmail() {
        assertTrue(EmailValidator.isValid("test@example.com"));
        assertTrue(EmailValidator.isValid("test.email@example.com"));
        assertTrue(EmailValidator.isValid("test.email@subdomain.example.com"));
    }

    @Test
    public void testInvalidEmail() {
        assertFalse(EmailValidator.isValid("test@.com"));
        assertFalse(EmailValidator.isValid("test@example"));
        assertFalse(EmailValidator.isValid("test@.example.com"));
        assertFalse(EmailValidator.isValid("test@.example.com."));
        assertFalse(EmailValidator.isValid("test.email+label@example.com"));

    }
}
