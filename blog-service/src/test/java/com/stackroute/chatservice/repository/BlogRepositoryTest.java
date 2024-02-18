package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.exception.BlogIdDoesNotExistException;
import com.stackroute.chatservice.model.Blog;
import com.stackroute.chatservice.service.BlogService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BlogRepositoryTest {

    @Mock
    BlogRepository repository;

    Blog blog;

    @InjectMocks
    BlogService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        blog = new Blog(1,"My experience with RentHUB", "Rishabh", "I Discovered a " +
                "wide range of budget-friendly equipment options available for rent on platform.cost-effectiveness ensures you can access the tools you need without breaking the bank. Experienced top-notch service from the website.");
    }

    @AfterEach
    void tearDown(){
        blog=null;
    }


    @Test
    public void givenBlogToSaveShouldReturnSavedBlog(){
        when(repository.save(any())).thenReturn(blog);
        assertEquals(blog,repository.save(blog));
        //assertEquals(1,patient.getPatientId());
        verify(repository,times(1)).save(any());
    }

    @Test
    public void givenDuplicateBlogIDToSaveThenShouldNotSaveBlog(){
        when(repository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class,()->{
            repository.save(blog);
        });
        verify(repository,times(1)).save(any());
    }


//    @Test
//    public void giveBlogIdToDeleteThenShouldNotReturnDeletedBlog() throws BlogIdDoesNotExistException {
//        when(repository.findById(blog.getBlogId())).thenReturn(Optional.empty());
//        boolean deletedPatient = service.deleteBlogById(1);
//        verify(repository,times(1)).findById(blog.getBlogId());
//    }


}