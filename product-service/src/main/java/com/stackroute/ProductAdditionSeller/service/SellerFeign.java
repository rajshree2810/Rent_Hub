package com.stackroute.ProductAdditionSeller.service;

import com.stackroute.ProductAdditionSeller.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-service",url = "http://localhost:8065")
public interface SellerFeign {
    @GetMapping("user/{id}")
    public User getSellerDetailById(@PathVariable("id") String id);
}
