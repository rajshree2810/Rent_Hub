package com.stackroute.producthistory.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private String customerId;
    private String productId;
    private String paymentId;
}
