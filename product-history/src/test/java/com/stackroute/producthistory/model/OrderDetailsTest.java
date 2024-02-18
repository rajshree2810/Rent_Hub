package com.stackroute.producthistory.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailsTest {

    OrderDetails orderDetails;

    @BeforeEach
    void setUp() {
        orderDetails = new OrderDetails();
    }

    @AfterEach
    void tearDown() {
        orderDetails = null;
    }

    @Test
    public void shouldGetOrderDetails(){
        orderDetails.setCustomerId("01");
        assertEquals("01",orderDetails.getCustomerId());
    }

}