package com.stackroute.ProductAdditionSeller.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product product;
    @BeforeEach
    void setUp() {
        product = new Product();
    }

    @AfterEach
    void tearDown() {
        product = null;
    }

    @Test
    public void shouldGetProductID(){
        product.setProductId("01");
        assertEquals("01",product.getProductId());
    }


}