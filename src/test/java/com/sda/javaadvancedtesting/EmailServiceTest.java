package com.sda.javaadvancedtesting;

import com.sda.javaadvancedtesting.model.EmailSender;
import com.sda.javaadvancedtesting.services.EmailService;
import org.junit.jupiter.api.Test;

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
}
