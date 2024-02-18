package com.stackroute.ProductAdditionSeller.repository;

import com.stackroute.ProductAdditionSeller.model.Product;
import com.stackroute.ProductAdditionSeller.model.User;
import com.stackroute.ProductAdditionSeller.service.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @Mock
    ProductRepository productRepository;

    Product product, product1;
    User user, user1;
    List<Product> productList;

    @InjectMocks
    ProductServiceImpl service;

    @BeforeEach
    void setUpUser() {
        MockitoAnnotations.initMocks(this);
        user = new User("1", "rishabh", "mishra", "rm@gmail.com", "12345678", "tpls", "M", "08/08/2020",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE");
//
        user1 = new User("2", "rohit", "sharma", "rs@gmail.com", "87654321", "ecity", "M", "17/08/2010",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.easeus.com%2Fknowledge-center%2Fram.html&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAJ");
//
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
    public void givenProductShouldReturnSavedProduct() {
        //when product repository is called return user
        when(productRepository.save(any())).thenReturn(product);
        //now checking
        assertEquals(product, productRepository.save(product));
        verify(productRepository, times(1)).save(any());
    }

    @Test
    public void givenDuplicateProductIDToSaveShouldNotSaveProduct() {
        //we save the data and check if data is already there and if it's there we throw runtime exception
        when(productRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            productRepository.save(product);
        });
        verify(productRepository,times(1)).save(any());
    }


    //all the values of productList 1  passed into prouctList and compared
    @Test
    public void givenProductShouldReturnProductListByName(){
        productRepository.save(product);
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productList1 = service.getAllProduct();
        assertEquals(productList, productList1);
        verify(productRepository, times(1)).save(product);
        verify(productRepository,times(1)).findAll();
    }

//    @Test
//    public void givenProductIdtoDeleteShouldNotReturnDeletedProduct(){
//        when(productRepository.findById(product.getProductId())).thenReturn(Optional.empty());
//
//    }
}


