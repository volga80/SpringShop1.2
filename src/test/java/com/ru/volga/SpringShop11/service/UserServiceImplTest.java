package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.dao.UserRepository;
import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl service;
    @Mock
    UserRepository mockUserRepository;
    @Mock
    PasswordEncoder mockEncoder;
    @Mock
    UserService mockUserService;

    @Test
    void findByUsername_ShouldBeReturnUser() {
        User user = User.builder().name("Andrey").build();
        mockUserRepository.save(user);

        when(mockUserRepository.findFirstByName(user.getName())).thenReturn(user);
        User actual = service.findByUsername(user.getName());

        assertNotNull(actual);
        assertEquals(actual, user);
    }

    @Test
    void save_ShouldSaveUser() {
        User user = new User();

        service.save(user);

        verify(mockUserRepository, times(1)).save(user);
    }

    @Test
    void getAll() {
        User user = new User();
        User user1 = new User();
        List<User> users = Arrays.asList(user, user1);
        UserDTO dto1 = new UserDTO();
        UserDTO dto2 = new UserDTO();
        List<UserDTO> dtos = Arrays.asList(dto1, dto2);

        when(mockUserRepository.findAll()).thenReturn(users);
        when(service.toDto(user)).thenReturn(dto1);
        when(service.toDto(user1)).thenReturn(dto2);
        List<UserDTO> actual = service.getAll();

        assertNotNull(actual);
        assertEquals(actual, dtos);
    }

    @Test
    void findByName() {
        User user = new User();
        user.setName("Andrey");

        service.findByName(user.getName());

        verify(mockUserRepository, times(1)).findFirstByName(user.getName());
    }

    @Test
    void updateProfile_WhenUserIsNull() {
        UserDTO dto = new UserDTO();

        when(mockUserRepository.findFirstByName(dto.getUsername())).thenReturn(null);

        assertThrows(Exception.class, () -> {
            service.updateProfile(dto);
            throw new RuntimeException("User not found by name" + dto.getUsername());
        });
    }

    @Test
    void updateProfile_WhenUserNotNullAndChanged() {
        UserDTO dto = UserDTO.builder().username("A").email("B").password("C").matchingPassword("C").build();
        User user = new User();

        when(mockUserRepository.findFirstByName(dto.getUsername())).thenReturn(user);
        service.updateProfile(dto);

        verify(mockUserRepository, times(1)).findFirstByName(dto.getUsername());
        verify(mockEncoder, times(1)).encode(dto.getPassword());
        verify(mockUserRepository, times(1)).save(user);
    }
}