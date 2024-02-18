package com.stackroute.emailservice.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailDetailsTest {

    EmailDetails details;

    @BeforeEach
    void setUp() {
        details = new EmailDetails();
    }

    @AfterEach
    void tearDown() {
        details=null;
    }

    @Test
    public void shouldGetEmailRecipient(){
        details.setRecipient("ram");
        assertEquals("ram", details.getRecipient());
    }
}