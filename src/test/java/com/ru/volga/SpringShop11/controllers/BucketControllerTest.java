package com.ru.volga.SpringShop11.controllers;

import com.ru.volga.SpringShop11.service.BucketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BucketControllerTest {

    @Mock
    private BucketService bucketService;

    @InjectMocks
    private BucketController bucketController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bucketController).build();
    }


    @Test
    void aboutBucketTest() {
    }

    @Test
    void deleteProductTest() {
    }

    @Test
    void deleteOneProductTest() {
    }
}