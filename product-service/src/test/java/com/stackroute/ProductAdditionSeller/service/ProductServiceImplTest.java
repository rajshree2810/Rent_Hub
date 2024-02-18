package com.stackroute.ProductAdditionSeller.service;

import com.stackroute.ProductAdditionSeller.exception.ProductNotFoundException;
import com.stackroute.ProductAdditionSeller.model.Product;
import com.stackroute.ProductAdditionSeller.model.User;
import com.stackroute.ProductAdditionSeller.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductServiceImpl service;


    Product product, product1;
    User user, user1;
//    List<Product> productList;


    @BeforeEach
    void setUpUser() {
        MockitoAnnotations.initMocks(this);
        user = new User("1", "rishabh", "mishra", "rm@gmail.com", "12345678", "tpls", "M", "08/08/2020",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE");

//        user1 = new User("2", "rohit", "sharma", "rs@gmail.com", "87654321", "ecity", "M", "17/08/2010",
//                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.easeus.com%2Fknowledge-center%2Fram.html&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAJ");

    }

    @BeforeEach
    void setUpProduct() {
        MockitoAnnotations.initMocks(this);
        Date a = new Date(12, 12, 2017);
        product = new Product("1", "JCB", "5000", "BMW", "this is best JCB", a, a, "air-pump",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.easeus.com%2Fknowledge-center%2Fram.html&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAJ",
                user);
        product1 = new Product("2", "tractor", "100", "BMW", "best tractor", a, a, "land",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE", user1);
//        productList.add(product);
//        productList.add(product1);
    }

    @AfterEach
    void tearDownUser() {
        user = null;
        user1 = null;
    }

    @AfterEach
    void tearDownProduct() {
        product = null;
        product1 = null;
    }

    @Test
    public void givenProductToSaveShouldReturnSaveProduct(){
        Product product2 = repository.save(product);
        assertNotNull(product2);    //checking if product2 is getting value or not
        assertEquals(product.getProductId(), product2.getProductId());
    }

    @Test
    public void ShouldReturnListOfProducts(){
        List<Product> list = repository.findAll();
        assertNotNull(list);
    }

//    @Test
//    public void givenProductIDToSaveShouldNotReturnIfProductNotFound() {
//
//        // Mock the behavior of repository.findById to return an empty Optional
//        when(repository.findById(anyString())).thenReturn(Optional.empty());
//
//        // Assert that a ProductNotFoundException is thrown when calling getProductById
//        Assertions.assertThrows(ProductNotFoundException.class, () -> {
//            service.getProductById("nonExistentProductId");
//        });
//
//        // Verify that repository.findById was called exactly once with the given ID
//        verify(repository, times(1)).findById("nonExistentProductId");
//
//    }

}