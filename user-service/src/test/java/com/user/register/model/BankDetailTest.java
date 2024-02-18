package com.user.register.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankDetailTest {

    BankDetail detail;

    @BeforeEach
    void setUp() {
        detail = new BankDetail();
    }

    @AfterEach
    void tearDown() {
        detail = null;
    }

    @Test
    public void shouldGetBankName(){
        detail.setBankName("HDFC");
        assertEquals("HDFC", detail.getBankName());
    }


}