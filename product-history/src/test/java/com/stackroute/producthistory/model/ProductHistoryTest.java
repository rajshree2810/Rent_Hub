package com.stackroute.producthistory.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductHistoryTest {

    ProductHistory history;

    @BeforeEach
    void setUp() {
        history = new ProductHistory();
    }

    @AfterEach
    void tearDown() {
        history = null;
    }

    @Test
    public void shouldGetOrderDetails(){
        history.setPaymentId("01");
        assertEquals("01",history.getPaymentId());
    }

}