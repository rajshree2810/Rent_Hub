package com.stackroute.chatservice.service;
import com.stackroute.chatservice.exception.BlogAlreadyExistException;
import com.stackroute.chatservice.exception.BlogIdDoesNotExistException;
import com.stackroute.chatservice.model.Blog;
import com.stackroute.chatservice.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService implements BlogServiceDAO {


    @Autowired
    BlogRepository repository;
    @Override
    public Blog addBlog(Blog blog) throws BlogAlreadyExistException {
        Blog addedBlog;
        Optional<Blog> optionalBlog = repository.findById(blog.getBlogId());
        if (optionalBlog.isPresent()) {
            throw new BlogAlreadyExistException("Duplicate - Blog with the given ID already exists");
        }
        else
            addedBlog=repository.save(blog);
        return addedBlog;
    }

    @Override
    public List<Blog> getAllBlogs() {
        return repository.findAll();
    }

    @Override
    public boolean deleteBlogById(int blogId) throws BlogIdDoesNotExistException {
        Optional<Blog> optionalBlog = repository.findById(blogId);
        if (optionalBlog.isPresent()) {
            repository.deleteById(blogId);
            return true;
        } else {
            throw new BlogIdDoesNotExistException("Blog ID does not exist");
        }
    }

    @Override
    public Blog updateBlog(Blog blog) throws BlogIdDoesNotExistException {
        Optional<Blog> optionalBlog = repository.findById(blog.getBlogId());
        if (optionalBlog.isPresent()) {
            return repository.save(blog);
        } else {
            throw new BlogIdDoesNotExistException("Blog ID does not exist");
        }
    }

}

