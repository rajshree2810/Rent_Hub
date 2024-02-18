package com.stackroute.paymentservice.repository;

import com.stackroute.paymentservice.razorpay.controller.OrderResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<OrderResponse,String> {
}
