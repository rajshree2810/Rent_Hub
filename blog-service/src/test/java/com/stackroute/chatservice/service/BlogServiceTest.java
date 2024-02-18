package com.stackroute.chatservice.service;

import com.stackroute.chatservice.model.Blog;
import com.stackroute.chatservice.repository.BlogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogServiceTest {
    @Autowired
            BlogRepository repository;

    @Autowired
            BlogService service;

    Blog blog;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        blog = new Blog(1,"My experience with RentHUB", "Rishabh", "I Discovered a " +
                "wide range of budget-friendly equipment options available for rent on platform.cost-effectiveness ensures you can access the tools you need without breaking the bank. Experienced top-notch service from the website.");
    }

    @AfterEach
    void tearDown(){
        blog=null;
    }

    @Test
    public void  givenBlogToSaveShouldReturnSavedBlog(){
        Blog blog1 = repository.save(blog);
        assertNotNull(blog1);
        assertEquals(blog.getBlogId(),blog1.getBlogId());
    }


}