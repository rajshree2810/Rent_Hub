package com.user.register.repository;

import com.user.register.model.BankDetail;
import com.user.register.model.User;
import com.user.register.service.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    @Mock
    UserRepository repository;

    User user;
    BankDetail details;

    @InjectMocks
    UserServiceImpl service;


    @BeforeEach
    void setUpBankDetails(){
        MockitoAnnotations.initMocks(this);
        details = new BankDetail("HDFC", 12345678L,"HDFC","rishabh");

    }

    @BeforeEach
    void setUpUser(){
        MockitoAnnotations.initMocks(this);

        user = new User("1","password", "rishabh", "mishra", "rm@gmail.com", "12345678", "tpls", "M", "08/08/2020",
                "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.edmunds.com%2Fram%2F2500%2F2024%2F&psig=AOvVaw3Re--CchdYIy9q7dTsJA6C&ust=1692637667723000&source=images&cd=vfe&opi=89978449&ved=0CBAQjRxqFwoTCJjP0urc64ADFQAAAAAdAAAAABAE",
                details);
//
    }

    @AfterEach
    void tearDown(){
        details=null;
        user=null;
    }

    @Test
    public void givenUserShouldReturnSavedUser() {
        //when product repository is called return user
        when(repository.save(any())).thenReturn(user);
        //now checking
        assertEquals(user, repository.save(user));
        verify(repository, times(1)).save(any());
    }

    @Test
    public void givenDuplicateUserIDToSaveShouldNotSave() {
        //we save the data and check if data is already there and if it's there we throw runtime exception
        when(repository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            repository.save(user);
        });
        verify(repository,times(1)).save(any());
    }

    @Test
    public void givenEmailShouldFindUser(){

        when(repository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));

        //call the method being tested
        Optional<User> user1 = repository.findUserByEmail(user.getEmail());

        //verify result
        assertTrue(user1.isPresent());
        assertEquals(user, user1.get());

        // Verify that the repository's findUserByEmail method was called once with the correct argument
        verify(repository, times(1)).findUserByEmail(user.getEmail());


    }
}
