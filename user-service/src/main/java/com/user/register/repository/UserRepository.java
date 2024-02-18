package com.user.register.repository;

import com.user.register.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    //@Query(value = "{'email':?0}")
    public Optional<User> findUserByEmail(String email);

    @Query(value = "{}", fields = "{'userId': 1}")
    List<User> findAllUserNames();
}
