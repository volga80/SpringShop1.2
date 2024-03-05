package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.domain.Bucket;
import com.ru.volga.SpringShop11.domain.Product;
import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.dto.BucketDTO;
import com.ru.volga.SpringShop11.dto.BucketDetailDTO;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface BucketService {

    Bucket createBucket(User user, List<Long> productIds);

    void addProducts(Bucket bucket, List<Long> productIds);

    BucketDTO getBucketDTOByUser(String name);
    Bucket getBucketByUser(String name);

    void deleteOneProduct(Long bucketId, Long productId);

}
