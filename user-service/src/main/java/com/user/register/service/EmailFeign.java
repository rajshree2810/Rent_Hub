package com.user.register.service;

import com.user.register.model.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "email-service", url = "http://localhost:8085")
public interface EmailFeign {
    @PostMapping("email/sendMail")
    public String sendMail(@RequestBody Email email);
}
