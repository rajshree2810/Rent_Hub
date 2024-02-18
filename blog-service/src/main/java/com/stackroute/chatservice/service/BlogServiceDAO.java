package com.stackroute.chatservice.service;

import com.stackroute.chatservice.exception.BlogAlreadyExistException;
import com.stackroute.chatservice.exception.BlogIdDoesNotExistException;
import com.stackroute.chatservice.model.Blog;

import java.util.List;

public interface BlogServiceDAO {
    public Blog addBlog(Blog blog) throws BlogAlreadyExistException;
    public List<Blog> getAllBlogs();
    boolean deleteBlogById(int blogId) throws BlogIdDoesNotExistException;
    public Blog updateBlog(Blog blog) throws BlogIdDoesNotExistException;
}
