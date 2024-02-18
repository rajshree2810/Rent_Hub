package com.stackroute.chatservice.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    Message message;

    @BeforeEach
    void setUp(){
        message = new Message();
    }

    @AfterEach
    void tearDown(){
        message=null;
    }

    @Test
    public void shouldReturnSenderEmail(){
        message.setSenderEmail("rishabh@gmail.com");
        assertEquals("rishabh@gmail.com", message.getSenderEmail());
    }

}