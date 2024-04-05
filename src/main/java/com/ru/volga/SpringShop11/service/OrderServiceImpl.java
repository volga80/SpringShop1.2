package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.dao.BucketRepository;
import com.ru.volga.SpringShop11.dao.OrderRepository;
import com.ru.volga.SpringShop11.dao.ProductRepository;
import com.ru.volga.SpringShop11.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BucketRepository bucketRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BucketRepository bucketRepository, UserService userService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.bucketRepository = bucketRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public List<Order> getOrdersByUser(String name) {
        User user = userService.findByName(name);
        List<Order> orders = user.getOrdersList();
        return orders;
    }


    @Override
    @Transactional
    public Order createOrder(User user) {
        Order order = new Order();
        order.setUser(user);
        Bucket bucket = user.getBucket();
        order.setCreated(LocalDateTime.now());
        List<Product> products = new ArrayList<>(bucket.getBucketProducts());
        order.setOrderProducts(products);
        BigDecimal summa = new BigDecimal(0.0);
        for (Product product : products) {
            summa = summa.add(product.getPrice());
            productRepository.getById(product.getId()).setAmount(product.getAmount() - 1);
            productRepository.save(product);
        }
        order.setSum(summa);
        order.setStatus(OrderStatus.NEW);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder() {
        return new Order();
    }
}
