package com.stackroute.ProductAdditionSeller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.ProductAdditionSeller.model.Product;
import com.stackroute.ProductAdditionSeller.model.User;
import com.stackroute.ProductAdditionSeller.service.ProductServiceImpl;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @InjectMocks
    ProductController controller;

    @MockBean
    ProductServiceImpl service;

    Product product  = new Product();
    User user;
    MockMvc mvc;

    @BeforeEach
    void setUpProduct() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
        Date startDate = new Date(12, 12, 2017);
        Date endDate = new Date(12, 12, 2020);
        product.setProductId("1");
        product.setProductName("JCB");
        product.setProductPrice("5000");
        product.setProductBrand("BMW");
        product.setProductDescription("this is best JCB");
        //product.setStartDate(startDate);
        product.setEndDate(endDate);
        product.setProductCategory("air-pump");
        product.setProductImageUrl("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.easeus.com%2Fknowledge-center%2Fram.html&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAJ");
        product.setSellerDetails(user);
    }

    @BeforeEach
    void setUpUser() {
        MockitoAnnotations.initMocks(this);
        user = new User("1", "rishabh", "mishra", "rm@gmail.com", "12345678", "tpls", "M", "08/08/2020",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE");
    }

    @AfterEach
    void tearDownUser() {
        user = null;
    }

    @AfterEach
    void tearDownProduct() {
        product = null;
    }


//    @Test
//    public void whenPostObjectSuccess() throws Exception, JsonProcessingException {
//        Mockito.when(service.addProduct(product)).thenReturn(product);
//        mvc.perform(post("/product//addProduct/{userId}")
//
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(convertObject(product))).andExpect(MockMvcResultMatchers.status().isCreated());
//
//    }
//    private String convertObject(Object o) throws JsonProcessingException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(o);
//    }

}