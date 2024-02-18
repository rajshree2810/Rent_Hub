package com.user.register.controller;

import com.user.register.exception.UserAlreadyExist;
import com.user.register.exception.UserDoesNotExist;
import com.user.register.model.BankDetail;
import com.user.register.model.Credential;
import com.user.register.model.Email;
import com.user.register.model.User;
import com.user.register.service.EmailFeign;
import com.user.register.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8060","http://localhost:9999"})
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    EmailFeign emailFeign;

    @PostMapping("adduser")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            User user1 = userService.addUser(user);
            String emailStatus = generateEmail(new Email(),user);
            System.out.println(emailStatus);
            userService.sendMessage(createQueue(user));
            return new ResponseEntity<User>(user1, HttpStatus.CREATED);
        }catch (UserAlreadyExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("forgetpasswordmail")
    public ResponseEntity<?> forgetPasswordMail(@RequestParam String email){
        try {
            User user1 = userService.forgetPasswordEmail(email);
            String emailStatus = forgotPasswordEmail(new Email(),user1);
            System.out.println(emailStatus);
            return new ResponseEntity<User>(user1,HttpStatus.OK);
        }catch (UserDoesNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PutMapping("forgotpassword")
    public ResponseEntity<?> forgotPassword(@RequestBody User user){
        try {
            User user1 = userService.forgotPassword(user);
            userService.sendMessage(createQueue(user));
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }catch (UserDoesNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") String id){
        try {
            return new ResponseEntity<User>(userService.getUserById(id),HttpStatus.OK);
        }catch (UserDoesNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("updateprofile")
    public ResponseEntity<?> updateProfile(@RequestBody User user){
        try {
            return new ResponseEntity<>(userService.updateProfile(user),HttpStatus.OK);
        }catch (UserDoesNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping("addprofileimage")
    public ResponseEntity<?> addProfileImage(@RequestBody User user) throws UserDoesNotExist {
        try {

            System.out.println("Image url: "+ user.getImageUrl());
            return new ResponseEntity<>(userService.addProfileImage(user),HttpStatus.OK);
        }catch (UserDoesNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PutMapping("updatebankdetail")
    public ResponseEntity<?> updateBankProfile(@RequestBody BankDetail bankDetail,@RequestHeader("userId")String userId){
        try {
            System.out.println("User Id: " + userId);
            System.out.println(bankDetail.getBankName());
            User user=new User();
            user.setUserId(userId);
            user.setBankDetail(bankDetail);
            User user1 = userService.updateBankDetail(user);
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }catch (UserDoesNotExist e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    public Credential createQueue(User user){
        Credential credential = new Credential();
        credential.setUserId(user.getUserId());
        credential.setPassword(user.getPassword());
        return credential;
    }

    public String generateEmail(Email email,User user){
        email.setSubject("Welcome to RentHub");
        email.setRecipient(user.getEmail());
        email.setMsgBody("Welcome to RentHub. Your registered username is "
                           + user.getUserId() + ". \n" +
                           "Registered password is " + user.getPassword());
        return emailFeign.sendMail(email);
    }

    public String forgotPasswordEmail(Email email,User user){
        System.out.println("Sending Mail");
        email.setSubject("Reset Password");
        email.setRecipient(user.getEmail());
        email.setMsgBody(user.getFirstName()+" "+user.getLastName()+ " Please change your password with the following link:\n"
                + "http://localhost:4200/forgotpassword/false/"+user.getUserId());
        return emailFeign.sendMail(email);
    }

    @GetMapping("getallnames")
    public ResponseEntity<?> getAllUserNames() {
        try {
            List<String> userNames = userService.getAllUserNames();
            if (userNames.isEmpty()) {
                return new ResponseEntity<>("No users found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userNames, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while fetching user names.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
