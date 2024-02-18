package com.stackroute.recommendationservice.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//this class stores the product description
@Data
@Setter
@Getter
@Node
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String userProductId;
    private String productAddress;


}
