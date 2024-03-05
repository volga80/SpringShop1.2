package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.dao.BucketRepository;
import com.ru.volga.SpringShop11.dao.ProductRepository;
import com.ru.volga.SpringShop11.domain.Bucket;
import com.ru.volga.SpringShop11.domain.Product;
import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.dto.BucketDTO;
import com.ru.volga.SpringShop11.dto.BucketDetailDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }


    @Override
    public Bucket getBucketByUser(String name){
        User user = userService.findByName(name);
        Bucket bucket = user.getBucket();
        return bucket;
    }
    @Override
    public BucketDTO getBucketDTOByUser(String name) {
        User user = userService.findByName(name);
        if(user == null || user.getBucket() == null){
            return new BucketDTO();
        }
        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId = new HashMap<>();
        List<Product> products = user.getBucket().getProducts();
        for (Product product : products){
            BucketDetailDTO detail = mapByProductId.get(product.getId());
            if(detail == null){
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));
            } else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum() + Double.valueOf(product.getPrice().toString()));
            }
        }
        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;
    }


    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds){
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.saveAndFlush(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds){
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProducts(Bucket bucket, List<Long> productIds){
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    @Transactional
    public void deleteOneProduct(Long bucketId, Long productId) {
        Bucket bucket = bucketRepository.findById(bucketId).orElseThrow(() -> new RuntimeException("Bucket not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        bucket.getProducts().remove(product);
        bucketRepository.save(bucket);
    }
    }
