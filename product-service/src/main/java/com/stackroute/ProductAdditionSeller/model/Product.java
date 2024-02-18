package com.stackroute.ProductAdditionSeller.model;

//import jakarta.persistence.Id;
//import jakarta.persistence.Lob;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

//@Document(collation = "Products")
@Document(collection = "product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    private String productId ;
    private String productName;
    private String productPrice;
    private String productBrand;
    private String productDescription;
    private Date startData;
    private Date endDate;
    //private String productAvailableTime;
    //private String productAddress;
    //private String sellerEmail;
    private String productCategory;

    private String productImageUrl;
    private User sellerDetails;

}
