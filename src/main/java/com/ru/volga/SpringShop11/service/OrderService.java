package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.domain.Order;
import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.dto.BucketDTO;

import java.util.List;

public interface OrderService {

    Order createOrder(User user);

    Order updateOrder();

    List<Order> getOrdersByUser(String name);
}
