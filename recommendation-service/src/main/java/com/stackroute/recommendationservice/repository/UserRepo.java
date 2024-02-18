package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface UserRepo extends Neo4jRepository<User, String> {
    User findByUserAddress(String address);

    //finds a "User" node and a "Product" node that have the same address value, create a "sameAddress" relationship between them, and then return the "User" node.
    @Query("MATCH (u:User), (p:Product) WHERE (u.address) = $address AND (p.address) = $address CREATE (u)-[:sameAddress]->(p) RETURN u")
    HashSet<User> getProductBySameAddress(String address);

    @Query("MATCH (n:User {userId: $id}) DELETE DETACH n;")
    void deleteUser(String id);

}
