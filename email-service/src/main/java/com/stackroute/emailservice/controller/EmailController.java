package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.model.EmailDetails;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8065"})
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details) {
        System.out.println(details.toString());
        String status = service.sendSimpleMail(details);
        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        String status = service.sendMailWithAttachment(details);

        return status;
    }

}
