package com.ru.volga.SpringShop11.dao;

import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserRepositoryTest {

    @InjectMocks
    UserRepository mockRepository;

    @Mock
    UserService mockUserService;

    @Test
    void findFirstByName() {
        User user = User.builder().name("Andrey").build();

        when(mockRepository.save(user)).thenReturn(user);
        User expected  = mockRepository.save(user);
        User actual = mockRepository.findFirstByName(user.getName());

        assertEquals(actual, expected);
    }

    @Test
    void save() {
        User user = User.builder().name("d").build();

        User savedUser = mockRepository.save(user);

        assertNotNull(savedUser);
    }
}