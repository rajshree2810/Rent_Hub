package com.stackroute.producthistory.repository;

import com.stackroute.producthistory.model.Product;
import com.stackroute.producthistory.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductHistoryRepositoryTest {

    @Mock
    ProductHistoryRepository repository;

    Product product;

    User user, user1;

    @BeforeEach
    void setUpUser() {
        MockitoAnnotations.initMocks(this);
        user = new User("1", "rishabh", "mishra", "rm@gmail.com", "12345678", "tpls", "M", "08/08/2020",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE");
//
    }


    @BeforeEach
    void setUpProduct() {
        MockitoAnnotations.initMocks(this);

        product = new Product("1", "JCB", "5000", "BMW", "this is best JCB","sample_email@gmail.com","air_pump",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.easeus.com%2Fknowledge-center%2Fram.html&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAJ",
                user);

    }
    @AfterEach
    void tearDown() {
        product=null;
        user=null;
    }

//    @Test
//    public void givenProductShouldReturnSavedProduct() {
//        //when product repository is called return user
//        when(repository.save(any())).thenReturn(product);
//        //now checking
//        assertEquals(product, repository.save(product));
//        verify(repository, times(1)).save(any());
//    }

}