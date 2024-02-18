package com.stackroute.chatservice.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.stackroute.chatservice.exception.BlogIdDoesNotExistException;
import com.stackroute.chatservice.exception.BlogAlreadyExistException;
import com.stackroute.chatservice.model.Blog;
import com.stackroute.chatservice.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/blog")
public class BlogController {
    @Autowired
    BlogService service;

    @PostMapping("/addblog")
    public ResponseEntity<?> addBlog(@RequestBody Blog blog) {
        try {
            Blog addedBlog = service.addBlog(blog);
            return new ResponseEntity<Blog>(addedBlog, HttpStatus.CREATED);
        } catch (BlogAlreadyExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/viewall")
    public ResponseEntity<?> getAllBlogs() {
        List<Blog> blogList = service.getAllBlogs();
        return new ResponseEntity<List>(blogList, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable("blogId") String blogId) {
        try {
            boolean result = service.deleteBlogById(Integer.parseInt(blogId));
            if (result) {
                return new ResponseEntity<String>("Blog Record Deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Blog Id Does not exist in DB", HttpStatus.CONFLICT);
            }
        } catch (BlogIdDoesNotExistException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NumberFormatException exception) {
            return new ResponseEntity<>("Blog Id should always be a number", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog) {
        try {
            Blog updatedBlog = service.updateBlog(blog);
            return new ResponseEntity<Blog>(updatedBlog, HttpStatus.OK);
        } catch (BlogIdDoesNotExistException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }



}

