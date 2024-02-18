package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Message;
//import com.stackroute.chatservice.service.ChatServiceImpl;
import com.stackroute.chatservice.services.ChatServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChatRepositoryTest {

    @Mock
    ChatRepository repository;

    @InjectMocks
    ChatServiceImpl service;

    Chat chat1;
    List<Message> messageList;

    @BeforeEach
    void setMessageList(){
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("rishabh@example.com", new Date(), "Hello, Himanshu!"));
        messages.add(new Message("himanshu@example.com", new Date(), "Hi, Rishabh!"));
        messages.add(new Message("rishabh@example.com", new Date(), "How's it going?"));
        messages.add(new Message("himanshu@example.com", new Date(), "I'm good, thanks!"));

    }

    @BeforeEach
    void setUpChat(){
        MockitoAnnotations.initMocks(this);
        chat1 = new Chat(1,"rishabh","himanshu", messageList);
    }

    @AfterEach
    void tearDown(){
        chat1=null;
        messageList=null;
    }

    @Test
    public void givenProductShouldReturnSavedProduct() {
        //when product repository is called return user
        when(repository.save(any())).thenReturn(chat1);
        //now checking
        assertEquals(chat1, repository.save(chat1));
        verify(repository, times(1)).save(any());
    }



}