package com.stackroute.chatservice.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlogTest {

    Blog blog;

    @BeforeEach
    void setUp() {
        blog = new Blog();
    }

    @AfterEach
    void tearDown() {
        blog=null;
    }

    @Test
    public void ShouldReturnBlogId(){
        blog.setBlogId(1);
        assertEquals(1, blog.getBlogId());
    }
}