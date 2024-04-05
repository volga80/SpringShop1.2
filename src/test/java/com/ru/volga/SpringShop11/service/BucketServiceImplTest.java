package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.dao.BucketRepository;
import com.ru.volga.SpringShop11.dao.ProductRepository;
import com.ru.volga.SpringShop11.dao.UserRepository;
import com.ru.volga.SpringShop11.domain.Bucket;
import com.ru.volga.SpringShop11.domain.Role;
import com.ru.volga.SpringShop11.domain.User;
import org.hibernate.query.derived.AnonymousTupleType;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BucketServiceImplTest {
    @InjectMocks
    BucketServiceImpl bucketServiceImpl;
    @Mock
    BucketService bucketService;
    @Mock
    BucketRepository bucketRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    UserService userService;
    @Mock
    OrderService orderService;
    @Test
    void getBucketByUserTest(){
        User user = new User();
        Bucket bucket = new Bucket();
        user.setBucket(bucket);
        when(userService.findByName(any(String.class))).thenReturn(user);

        Bucket actual = bucketService.getBucketByUser(user.getName());

        assertEquals(bucket, actual);
    }

    @Test
    void addProductTest(){

    }
}