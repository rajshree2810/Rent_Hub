package com.stackroute.chatservice.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseSequenceTest {

    DatabaseSequence data;

    @BeforeEach
    void setUp(){
        data = new DatabaseSequence();
    }

    @AfterEach
    void tearDown(){
        data = null;
    }

    @Test
    public void shouldReturnId(){
        data.setId("01");
        assertEquals("01", data.getId());
    }

}