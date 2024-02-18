package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.Product;
import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends Neo4jRepository<Product, String> {

    Product findByProductAddress(String productAddress);

    //This query connects a "User" node to a "Product" node based on the common address property.
    @Query("MATCH (u:User {userAddress: $userAddress}), (p:Product {address: $productAddress}) MERGE (u)-[r:SAME_ADDRESS]->(p)")
    void createAddressRelationshipWithUser(String userAddress, String productAddress);


}
