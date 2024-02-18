package com.user.register.service;

import com.user.register.exception.UserAlreadyExist;
import com.user.register.exception.UserDoesNotExist;
import com.user.register.model.Credential;
import com.user.register.model.User;
import com.user.register.repository.UserRepository;
import org.bson.BsonBinarySubType;
import org.bson.json.JsonObject;
import org.bson.types.Binary;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Collectors;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceDAO{
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

   //to add new user in website
    @Override
    public User addUser(User user) throws UserAlreadyExist {
        Optional<User> optionalUser=userRepository.findById(user.getUserId());
        Optional<User> optionalUser2=userRepository.findUserByEmail(user.getEmail());

        if (optionalUser.isEmpty()&&optionalUser2.isEmpty()){
            userRepository.save(user);
            return user;
        }else {
            throw new UserAlreadyExist("Already Exist");
        }
    }

    //send forgot password email to registered email id
    @Override
    public User forgetPasswordEmail(String email) throws UserDoesNotExist {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new UserDoesNotExist("User does not exist");
        }
    }

    //reset password
    @Override
    public User forgotPassword(User user) throws UserDoesNotExist {
        Optional<User> optionalUser=userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()){
            User user1 = optionalUser.get();
            user1.setPassword(user.getPassword());
            userRepository.save(user1);
            return user1;
        }else {
            throw new UserDoesNotExist("User does not exist");
        }
    }

    @Override
    public User getUserById(String id) throws UserDoesNotExist {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new UserDoesNotExist("User with id does not exist");
        }
    }

    //update profile details of user in user db
    @Override
    public User updateProfile(User user) throws UserDoesNotExist {
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if(optionalUser.isPresent()){
            user.setImageUrl(optionalUser.get().getImageUrl());
            user.setBankDetail(optionalUser.get().getBankDetail());
            return userRepository.save(user);
        }else {
            throw new UserDoesNotExist("User with id does not exist");
        }
    }


    @Override
    public boolean addProfileImage(User user) throws UserDoesNotExist{
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()){
            User user1 = optionalUser.get();
            user1.setImageUrl(user.getImageUrl());
            userRepository.save(user1);
            return true;
        }else throw new UserDoesNotExist("User does not exist");
    }

    @Override
    public User updateBankDetail(User user) throws UserDoesNotExist {
        Optional<User> optionalUser=userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()){
            User user1=optionalUser.get();
            user1.setBankDetail(user.getBankDetail());
            userRepository.save(user1);
            return user1;
        }else {
            throw new UserDoesNotExist("User does not exist");
        }
    }

    public List<String> getAllUserNames() {
        List<User> users = userRepository.findAllUserNames();
        return users.stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
    }

    //Sending message to queue of rabbit mq
    public void sendMessage(Credential credential){
        rabbitTemplate.convertAndSend(exchange, routingKey, credential);
    }

}
