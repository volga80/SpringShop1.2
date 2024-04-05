package com.ru.volga.SpringShop11.dao;

import com.ru.volga.SpringShop11.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
