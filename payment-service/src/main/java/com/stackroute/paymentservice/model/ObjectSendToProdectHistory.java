package com.stackroute.paymentservice.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectSendToProdectHistory {
    private String customerId;
    private String productId;
    private String paymentId;
}
