package com.stackroute.emailservice.controller;

import com.stackroute.emailservice.model.EmailDetails;
import com.stackroute.emailservice.service.EmailServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class EmailControllerTest {

    @InjectMocks
    EmailController controller;

    @MockBean
    EmailServiceImpl service;

    EmailDetails emailDetails;

    MockMvc mvc;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();

        emailDetails = new EmailDetails();
        emailDetails.setRecipient("recipient@gmail.com");
        emailDetails.setSubject("Test Subject");
        emailDetails.setMsgBody("Test Message");
    }

    @AfterEach
    void tearDown() {
        emailDetails=null;
    }

//    @Test
//    public void whenPostObjectSuccess() throws Exception, JsonProcessingException {
//        Mockito.when(service.addProduct(product)).thenReturn(product);
//        mvc.perform(post("/product//addProduct/{userId}")
//
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(convertObject(product))).andExpect(MockMvcResultMatchers.status().isCreated());
//
//    }
//    private String convertObject(Object o) throws JsonProcessingException{
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(o);
//    }
}