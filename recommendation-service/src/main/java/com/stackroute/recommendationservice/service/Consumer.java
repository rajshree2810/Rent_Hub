package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.model.Product;
import com.stackroute.recommendationservice.model.User;
import com.stackroute.recommendationservice.repository.ProductRepo;
import com.stackroute.recommendationservice.repository.UserRepo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;

    @RabbitListener(queues = {"${rabbitmq.queue1.name}"} )
    public void consumeUserDetailsFromQueue(User user){
        System.out.println("User details are : " +user);
        userRepo.save(user);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"} )
    public void consumeProductAddressFromQueue(Product product){
        System.out.println("User details are : " +product);
        productRepo.save(product);
    }


}
