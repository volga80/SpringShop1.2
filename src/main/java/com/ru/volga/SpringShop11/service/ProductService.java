package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.domain.Product;
import com.ru.volga.SpringShop11.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    void addToUserBucket(Long productId, String username, Integer amountToBucket);

    void addProduct(ProductDTO dto);

    boolean save(ProductDTO dto);

    boolean setAmountProductByIdToZero(Long productId);

    Product getProductById(Long productId);
}
