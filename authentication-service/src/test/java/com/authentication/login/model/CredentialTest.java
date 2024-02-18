package com.authentication.login.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CredentialTest {
    Credential credential;

    @BeforeEach
    void setUp(){
        credential = new Credential();
    }

    @AfterEach
    void tearDown(){
        credential = null;
    }

    @Test
    public void shouldReturnPassword(){
        credential.setPassword("password");
        assertEquals("password", credential.getPassword());
    }


}