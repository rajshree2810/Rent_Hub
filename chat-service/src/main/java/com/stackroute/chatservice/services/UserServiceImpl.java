package com.stackroute.chatservice.services;

import com.stackroute.chatservice.exceptions.UserAlreadyExistException;
import com.stackroute.chatservice.exceptions.UserNotFoundException;
import com.stackroute.chatservice.model.User;
import com.stackroute.chatservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getall() throws UserNotFoundException {
        List<User> users=userRepository.findAll();
        if (users.isEmpty()){
            throw new UserNotFoundException();
        }else {
           return users;
        }
    }

    @Override
    public User addUser(User user) throws UserAlreadyExistException {
       Optional<User> user1=userRepository.findById(user.getUserName());

       if (user1.isPresent()){
           throw new UserAlreadyExistException();
       }else {
           return userRepository.save(user);
       }
    }

    @Override
    public User getUserByUserName(String username) throws UserNotFoundException {
        Optional<User> user1=userRepository.findById(username);

        if (user1.isPresent()){
            return user1.get();
        }else {
            throw new UserNotFoundException();
        }
    }

}
