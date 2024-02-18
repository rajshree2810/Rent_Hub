package com.stackroute.paymentservice.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSuccesDetail {
    private String customerId;
    private String[] productId;
    private String paymentId;
}
