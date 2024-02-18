package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.EmailDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    private EmailDetails emailDetails;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        // Initialize emailDetails
        emailDetails = new EmailDetails();
        emailDetails.setRecipient("recipient@gmail.com");
        emailDetails.setSubject("Test Subject");
        emailDetails.setMsgBody("Test Message");
    }

    @AfterEach
    void tearDown() {
        emailDetails=null;
    }

//    @Test
//    void sendSimpleMail_Success() {
//        // Mocking the behavior of JavaMailSender
//        MimeMessage mimeMessageMock = Mockito.mock(MimeMessage.class);
//        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessageMock);
//
//        String result = emailService.sendSimpleMail(emailDetails);
//
//        assertEquals("Mail Sent Successfully", result);
//
//        // Verify that javaMailSender.send was called once
//        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
//    }

//    @Test
//    void sendSimpleMail_Error() {
        // Mocking the behavior of JavaMailSender to throw an exception
//        doThrow(new RuntimeException()).when(javaMailSender).send(any(SimpleMailMessage.class));

//        String result = emailService.sendSimpleMail(emailDetails);

//        assertEquals("Error while Sending Mail", result);

        // Verify that javaMailSender.send was called once
//        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));

        // Mocking the behavior of JavaMailSender to throw an exception
//        when(javaMailSender.send(any(SimpleMailMessage.class))).thenThrow(new RuntimeException());
//
//        String result = emailService.sendSimpleMail(emailDetails);
//
//        assertEquals("Error while Sending Mail", result);
//
//        // Verify that javaMailSender.send was called once
//        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));}
    }
