package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.ProductNotFoundException;
import com.stackroute.recommendationservice.model.Product;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.ProductRepo;
import com.stackroute.recommendationservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class ProductRecImpl implements ProductRecServiceDao{
    @Autowired
    ProductRepo productRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public User addNode(User user){
        User existingUser = userRepo.findByUserAddress(user.getUserAddress());
        if(existingUser == null){
            return userRepo.save(user);
        }else{
            connectUserAndProduct(existingUser.getUserAddress());
        }
        return null;
    }
    private void connectUserAndProduct(String userAddress) {
        Product product = productRepo.findByProductAddress(userAddress);
        if (product != null) {
            productRepo.createAddressRelationshipWithUser(userAddress, product.getProductAddress());
        }
    }

    @Override
    public Product addProductNode(Product product){
        Product existingProduct = productRepo.findByProductAddress(product.getProductAddress());
        if (existingProduct == null) {
            productRepo.save(product);
        } else {
            connectProductAndUser(existingProduct.getProductAddress());
        }
        return null;
    }

    private void connectProductAndUser(String productAddress) {
        User user = userRepo.findByUserAddress(productAddress);
        if (user != null) {
            productRepo.createAddressRelationshipWithUser(user.getUserAddress(), productAddress);
        }
    }

    @Override
    public HashSet<User> getProductByAddress(String address) throws ProductNotFoundException {
        HashSet<User> product= userRepo.getProductBySameAddress(address);
        if(product.isEmpty()){
            throw new ProductNotFoundException("product not found");
        }
        else
            return product;
    }

    @Override
    public String deleteNode(String id){
        Optional<User> userOptional = userRepo.findById(id);
        if(userOptional.isPresent()){
            userRepo.deleteUser(id);
            return "yes";
        }
        return "Not found";
    }
}
