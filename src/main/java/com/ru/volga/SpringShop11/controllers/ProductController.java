package com.ru.volga.SpringShop11.controllers;

import com.ru.volga.SpringShop11.dao.ProductRepository;
import com.ru.volga.SpringShop11.domain.Product;
import com.ru.volga.SpringShop11.dto.ProductDTO;
import com.ru.volga.SpringShop11.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LogManager.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductRepository productRepository;

    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public String list(Model model) {
        logger.info("Выполнен вход на вкладку products");
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @PostMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal, Integer amountToBuy) {
        if (amountToBuy == null || amountToBuy > productService.getProductById(id).getAmount()) {
            logger.info("Пользователь не ввел, либо ввел неверно количество товара");
            throw new RuntimeException("Введите количество товара соответствующее наличию");
        }
        if (principal == null) {
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName(), amountToBuy);
        return "redirect:/products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{id}/newAmount")
    public String setNewAmount(@PathVariable Long id, Integer newAmount) {
        Product product = productService.getProductById(id);
        product.setAmount(newAmount);
        productRepository.save(product);
        return "redirect:/products";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        return "product";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/new")
    public String addProduct(ProductDTO dto) {
        productService.addProduct(dto);
        return "redirect:/products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{productId}")
    public String removeProduct(@PathVariable Long productId, Model model) {
        model.addAttribute("productId", new Product().getId());
        return "products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{productId}")
    public String setAmountToZero(@PathVariable Long productId) {
        productService.setAmountProductByIdToZero(productId);
        return "redirect:/products";
    }
}
