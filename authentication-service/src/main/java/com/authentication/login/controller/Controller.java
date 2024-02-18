package com.authentication.login.controller;

import com.authentication.login.model.Credential;
import com.authentication.login.service.CredentialsImp;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/authentication/")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
public class Controller {

    @Autowired
    CredentialsImp service;
    @PostMapping("login")
    public ResponseEntity<?> loginUser(@RequestBody Credential credential){
        boolean result = service.validate(credential);
        if(result){
            String  token = generateToken(credential);
            HashMap hashMap = new HashMap();
            hashMap.put("token",token);
            return new ResponseEntity<HashMap>(hashMap, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<String>("Invalid credential not authorized", HttpStatus.UNAUTHORIZED);
        }
    }

    private String generateToken(Credential credential){
        return Jwts.builder().setSubject(credential.getUserId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+30000))
                .signWith(SignatureAlgorithm.HS256,"renthub").compact();
    }
}
