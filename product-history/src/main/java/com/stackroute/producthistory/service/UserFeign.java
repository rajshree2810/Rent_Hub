package com.stackroute.producthistory.service;

import com.stackroute.producthistory.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",url = "http://localhost:8065")
public interface UserFeign {
    @GetMapping("user/{id}")
    public User getUserById(@PathVariable String id);
}
