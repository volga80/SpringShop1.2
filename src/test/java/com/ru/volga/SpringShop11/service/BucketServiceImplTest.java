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
    BucketServiceImpl mockedBucketServiceImpl;
    @Mock
    BucketService mockedBucketService;
    @Mock
    BucketRepository mockedBucketRepository;
    @Mock
    ProductRepository mockedProductRepository;
    @Mock
    UserRepository mockedUserRepository;
    @Mock
    UserService mockedUserService;
    @Mock
    OrderService mockedOrderService;
    @Test
    void getBucketByUserTest(){
        User user = User.builder()
                .name("Andrey")
                .build();
        Bucket bucket = Bucket.builder()
                .build();
        user.setBucket(bucket);
        when(mockedUserService.findByName(user.getName())).thenReturn(user);

        Bucket actual = mockedBucketServiceImpl.getBucketByUser(user.getName());

        assertNotNull(actual);
        assertEquals(bucket, actual);
    }

    @Test
    void addProductTest(){
        Bucket bucket = new Bucket();
    }
}