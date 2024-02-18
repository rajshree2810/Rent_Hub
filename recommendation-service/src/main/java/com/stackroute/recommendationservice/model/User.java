package com.stackroute.recommendationservice.model;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

//this class store the user information and will send data to rabbitMq
@Data
@Getter
@Setter
@Node
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;
    private String userAddress;



}
