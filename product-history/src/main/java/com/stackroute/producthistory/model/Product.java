package com.stackroute.producthistory.model;

import lombok.*;
import org.springframework.data.annotation.Id;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId ;
    private String productName;
    private String productPrice;
    private String productBrand;
    private String productDescription;
    //private String productAvailableTime;
    //private String productAddress;
    private String sellerEmail;
    private String productCategory;
    private String productImageUrl;
    private User sellerDetails;
}
