package com.ru.volga.SpringShop11.service;

import com.ru.volga.SpringShop11.dao.ProductRepository;
import com.ru.volga.SpringShop11.domain.Bucket;
import com.ru.volga.SpringShop11.domain.Product;
import com.ru.volga.SpringShop11.domain.User;
import com.ru.volga.SpringShop11.dto.ProductDTO;
import com.ru.volga.SpringShop11.mapper.ProductMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper = ProductMapper.MAPPER;

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;


    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username, Integer amountToBucket) {
        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("Пользователь не найден" + username);
        }
        Bucket bucket = user.getBucket();
        for (int i = 0; i < amountToBucket; i++) {
            if (bucket == null) {
                Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
                user.setBucket(newBucket);
                userService.save(user);
            } else {
                bucketService.addProducts(bucket, Collections.singletonList(productId));
            }
        }
    }
    //todo переписать метод, с учетом количества

    @Override
    @Transactional
    public void addProduct(ProductDTO dto) {
        productRepository.save(mapper.toProduct(dto));
//        Product product = mapper.toProduct(dto);
//        Product savedProduct = productRepository.save(product);
    }

    @Override
    public boolean save(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .description(productDTO.getDescription())
                .amount(productDTO.getAmount())
                .build();
        productRepository.save(product);
        return true;
    }

    @Override
    @Transactional
    public boolean setAmountProductByIdToZero(Long productId) {
        Product product = getProductById(productId);
        product.setAmount(0);
        productRepository.save(product);
//        productRepository.deleteProductById(productId);
        return true;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.getById(productId);
    }
}
