package com.ru.volga.SpringShop11.controllers;

import com.ru.volga.SpringShop11.domain.Bucket;
import com.ru.volga.SpringShop11.dto.BucketDTO;
import com.ru.volga.SpringShop11.service.BucketService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/bucket")
public class BucketController {
    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @GetMapping
    public String aboutBucket(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("bucket", new BucketDTO());
        } else {
            BucketDTO bucketDTO = bucketService.getBucketDTOByUser(principal.getName());
            Bucket bucket = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucketDTO", bucketDTO);
            model.addAttribute("bucket", bucket);
        }
        return "bucket";
    }

    @GetMapping("/remove")
    public String removeProduct(Model model, @RequestParam Long bucketId, @RequestParam Long productId) {
        model.addAttribute("productId", productId);
        model.addAttribute("bucketId", bucketId);
        return "bucket";
    }

    @Transactional
    @PostMapping("/remove")
    public String removeOneProduct(@RequestParam("productId") Long productId, @RequestParam Long bucketId) {
        bucketService.removeOneProduct(bucketId, productId);
        return "redirect:/bucket";
    }

    @GetMapping("/orders")
    public String create(Model model, Principal principal){
        BucketDTO bucketDTO = bucketService.getBucketDTOByUser(principal.getName());
        model.addAttribute("bucketDTO", bucketDTO);
        model.addAttribute("principal", principal);
        return "redirect:/bucket";
    }


    @PostMapping("/orders")
    public String createOrder(Principal principal){
        bucketService.removeAllProducts(principal.getName());
        return "redirect:/bucket";
    }
}
