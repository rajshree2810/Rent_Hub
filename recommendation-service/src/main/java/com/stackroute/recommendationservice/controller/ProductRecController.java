package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.exception.ProductNotFoundException;
import com.stackroute.recommendationservice.model.Product;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.service.ProductRecImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("api/v1")
public class ProductRecController {

    @Autowired
    ProductRecImpl productRec;

    @PostMapping("/adduser")
    public ResponseEntity<?> addUserDetails(@RequestBody User user){
//        try {
        return new ResponseEntity<User>(productRec.addNode(user), HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            return  new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
//        }
    }

    @PostMapping("/addproduct")
    public ResponseEntity<?> addUserDetails(@RequestBody Product product){

        return new ResponseEntity<Product>(productRec.addProductNode(product), HttpStatus.CREATED);
//        } catch (ProductAlreadyExistsException e) {
//            return  new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
//        }
    }

    @GetMapping("recommend/{address}")
    public ResponseEntity<?> getRecommendationsByAddress(@PathVariable String address){
        try {
            HashSet<User> rec = productRec.getProductByAddress(address);
            return new ResponseEntity<HashSet>(rec, HttpStatus.OK);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<String>("product unavailable", HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNodeById(@PathVariable("id") String id) {
        String deletedNode = productRec.deleteNode(id);
        if(deletedNode.equals("yes")){
            return new ResponseEntity<String>("Node deleted", HttpStatus.OK);
        }
        return new ResponseEntity<String>("user not found", HttpStatus.CONFLICT);
    }}
