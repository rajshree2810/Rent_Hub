package com.stackroute.producthistory.service;

import com.stackroute.producthistory.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "product-service",url="http://localhost:8082")
public interface ProductFeign {
    @GetMapping("/product/{id}")
    public Product viewByProductId(@PathVariable("id") String id);
}
