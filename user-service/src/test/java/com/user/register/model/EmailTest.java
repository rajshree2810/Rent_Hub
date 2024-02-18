package com.user.register.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    Email email;

    @BeforeEach
    void setUp(){
        email= new Email();
    }

    @AfterEach
    void tearDown(){
        email = null;
    }

    @Test
    public void shouldGetRecipeint(){
        email.setRecipient("rishabh@gmail.com");
        assertEquals("rishabh@gmail.com", email.getRecipient());
    }

}