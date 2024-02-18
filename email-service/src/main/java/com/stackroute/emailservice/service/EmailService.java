package com.stackroute.emailservice.service;

import com.stackroute.emailservice.model.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails details);

    String sendMailWithAttachment(EmailDetails details);
}
