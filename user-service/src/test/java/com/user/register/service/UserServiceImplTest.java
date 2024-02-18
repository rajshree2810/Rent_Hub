package com.user.register.service;

import com.user.register.exception.UserAlreadyExist;
import com.user.register.model.BankDetail;
import com.user.register.model.User;
import com.user.register.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserRepository repository;

    @Autowired
    UserServiceImpl service;

    User user, user1;
    BankDetail details, details1;

    @BeforeEach
    void setUpBankDetails(){
        MockitoAnnotations.initMocks(this);
        details = new BankDetail("HDFC", 12345678L,"HDFC","rishabh");

        details1 = new BankDetail("ICICI", 12345678L,"HDFC","rishabh");

    }

    @BeforeEach
    void setUpUser(){
        MockitoAnnotations.initMocks(this);

        user = new User("1","password", "rishabh", "mishra", "rm@gmail.com", "12345678", "tpls", "M", "08/08/2020",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE",
                details);
//
        user1 = new User("2", "new_password", "John", "Doe", "john.doe@example.com", "98765432", "Some Company", "M", "10/10/1990",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE",
                details1);

    }

    @AfterEach
    void tearDown(){
        details=null;
        user=null;
    }

    @Test
    public void givenUserToSaveShouldReturnSavedUser(){
//        User user2 = repository.save(user);
//        assertNotNull(user2);    //checking if product2 is getting value or not
//        assertEquals(user.getUserId(), user2.getUserId());

        when(repository.findById(user.getUserId())).thenReturn(Optional.empty());
        when(repository.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(repository.save(user)).thenReturn(user1);

        try {
            assertNotNull(service.addUser(user));
        } catch (UserAlreadyExist e) {
            throw new RuntimeException(e);
        }

        try {
            assertEquals(user1, service.addUser(user));
        } catch (UserAlreadyExist e) {
            throw new RuntimeException(e);
        }

        verify(repository, times(1)).findById(user.getUserId());
        verify(repository, times(1)).findUserByEmail(user.getEmail());
        verify(repository, times(1)).save(user);

    }




}