package com.stackroute.chatservice.repository;
import com.stackroute.chatservice.model.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends MongoRepository<Blog, Integer> {



}
