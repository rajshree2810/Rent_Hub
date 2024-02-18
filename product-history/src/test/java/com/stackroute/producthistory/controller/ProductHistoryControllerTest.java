package com.stackroute.producthistory.controller;

import com.stackroute.producthistory.service.ProductHistoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProductHistoryControllerTest {

    @InjectMocks
    ProductHistoryController controller;

    @MockBean
    ProductHistoryImpl service;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}