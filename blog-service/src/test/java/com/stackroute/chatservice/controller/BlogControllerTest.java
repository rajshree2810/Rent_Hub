package com.stackroute.chatservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.chatservice.model.Blog;
import com.stackroute.chatservice.service.BlogService;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

class BlogControllerTest {

    @InjectMocks
    BlogController controller;
    @MockBean
    BlogService service;

    Blog blog= new Blog();
    MockMvc mvc;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        blog.setBlogId(1);
        blog.setBlogTitle("My experience with RentHUB");
        blog.setAuthorName("Rishabh");
        blog.setBlogContent("I Discovered a " +
                "wide range of budget-friendly equipment options available for rent on platform." +
                "cost-effectiveness ensures you can access the tools you need without breaking the bank." +
                " Experienced top-notch service from the website.");

    }

    @AfterEach
    void teardown(){
        blog=null;
    }

    @Test
    public void whenPostObjectSuccess() throws Exception, JsonProcessingException {
        Mockito.when(service.addBlog(blog)).thenReturn(blog);
        mvc.perform(MockMvcRequestBuilders.post("/api/blog/addblog")

                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObject(blog))).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    private String convertObject(Object o) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(o);
    }



}