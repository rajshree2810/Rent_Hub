package com.stackroute.chatservice.services;

import com.stackroute.chatservice.exceptions.UserAlreadyExistException;
import com.stackroute.chatservice.exceptions.UserNotFoundException;
import com.stackroute.chatservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getall() throws UserNotFoundException;

    User addUser(User user) throws UserAlreadyExistException;

    User getUserByUserName(String username)  throws UserNotFoundException;
}
