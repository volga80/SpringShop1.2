package com.ru.volga.SpringShop11.dao;

import com.ru.volga.SpringShop11.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product product);

    void deleteProductById(Long id);
}
