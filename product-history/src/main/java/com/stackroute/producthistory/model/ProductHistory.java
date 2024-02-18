package com.stackroute.producthistory.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Document(collection = "history")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductHistory {
    @Id
    String transaction_id= UUID.randomUUID().toString();
    Product rentedProduct;
    User customer;
    String paymentId;
    LocalDate orderDate= LocalDate.now();
    LocalDate deliveryDate= orderDate.plusDays(10);
}
