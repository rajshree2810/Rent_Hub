package com.stackroute.paymentservice.razorpay.controller;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
@Document
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @Id
    String customerId;
    String[] productId;
    String customerName;
    String email;
    String phoneNumber;
    BigInteger amount;

}
