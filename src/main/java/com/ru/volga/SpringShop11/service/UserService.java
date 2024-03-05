package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
    void save(User user);

    List<UserDTO> getAll();

    User findByName(String name);
    void updateProfile(UserDTO userDTO);
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
