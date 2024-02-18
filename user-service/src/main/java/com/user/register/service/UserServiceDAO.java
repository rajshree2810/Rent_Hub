package com.user.register.service;

import com.user.register.exception.UserAlreadyExist;
import com.user.register.exception.UserDoesNotExist;
import com.user.register.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserServiceDAO {
    public User addUser(User user)throws UserAlreadyExist;

    public User forgetPasswordEmail(String email)throws UserDoesNotExist;

    public User getUserById(String id)throws UserDoesNotExist;

    public User forgotPassword(User user)throws UserDoesNotExist;

    public User updateProfile(User user)throws UserDoesNotExist;

    public boolean addProfileImage(User user) throws UserDoesNotExist;

//    public MultipartFile getProfileImage(String userId)throws IOException;

    public User updateBankDetail(User user)throws UserDoesNotExist;


}
