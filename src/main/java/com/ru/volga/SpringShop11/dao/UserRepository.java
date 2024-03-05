package com.ru.volga.SpringShop11.dao;

import com.ru.volga.SpringShop11.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByName(String name);

    User save(User user);
}