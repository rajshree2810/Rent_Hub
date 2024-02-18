package com.stackroute.producthistory.controller;
import com.fasterxml.jackson.core.JsonParser;
import com.stackroute.producthistory.model.OrderDetails;
import com.stackroute.producthistory.model.Product;
import com.stackroute.producthistory.model.ProductHistory;
import com.stackroute.producthistory.service.ProductFeign;
import com.stackroute.producthistory.service.ProductHistoryImpl;
import com.stackroute.producthistory.service.UserFeign;
import lombok.Getter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("producthistory")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:9999"})
public class ProductHistoryController {
    @Autowired
    ProductHistoryImpl service;

    @Autowired
    UserFeign userFeign;

    @Autowired
    ProductFeign productFeign;

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void add(OrderDetails orderDetails){
        try {
            ProductHistory productHistory=new ProductHistory();
            Product product=productFeign.viewByProductId(orderDetails.getProductId());
            product.setSellerDetails(productFeign.viewByProductId(orderDetails.getProductId()).getSellerDetails());
            productHistory.setCustomer(userFeign.getUserById(orderDetails.getCustomerId()));
            productHistory.setRentedProduct(product);
            productHistory.setPaymentId(orderDetails.getPaymentId());
            service.addTransaction(productHistory);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("trackorders/{customer_id}")
    public ResponseEntity<?> getBuyedOrderByCustomerId(@PathVariable("customer_id")String id){
        try {
            return new ResponseEntity<>(service.getTransactionByCustomerId(id),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("soldorders/{username}")
    public ResponseEntity<?> getSoldOrders(@PathVariable("username")String username){
        try {
            return new ResponseEntity<>(service.getSoldProductById(username),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping("deleteorder/{order_id}")
    public ResponseEntity<?> deleteOrder(@PathVariable("order_id")String id){
        try {
            return new ResponseEntity<>(service.deleteOrder(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }


}
