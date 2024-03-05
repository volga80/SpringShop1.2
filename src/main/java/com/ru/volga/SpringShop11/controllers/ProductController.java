package com.ru.volga.SpringShop11.controllers;

import com.ru.volga.SpringShop11.domain.Product;
import com.ru.volga.SpringShop11.dto.ProductDTO;
import com.ru.volga.SpringShop11.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model){
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
        if(principal == null){
            return "redirect:/products";
        }
        productService.addToUserBucket(id, principal.getName());
        return "redirect:/products";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String newProduct(Model model){
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
    public String removeProduct(@PathVariable Long productId, Model model){
        model.addAttribute("productId", new Product().getId());
        return "products";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        productService.deleteProductById(productId);
        return "redirect:/products";
    }

}
