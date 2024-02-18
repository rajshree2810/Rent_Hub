package com.stackroute.chatservice.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    Chat chat;

    @BeforeEach
    void setUp(){
        chat = new Chat();
    }

    @AfterEach
    void tearDown(){
        chat = null;
    }

    @Test
    public void ShouldReturnChatId(){
        chat.setChatId(1);
        assertEquals(1,chat.getChatId());
    }

}