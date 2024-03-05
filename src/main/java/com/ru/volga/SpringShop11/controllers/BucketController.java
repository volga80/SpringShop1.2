package com.ru.volga.SpringShop11.controllers;

import com.ru.volga.SpringShop11.domain.Bucket;
import com.ru.volga.SpringShop11.dto.BucketDTO;
import com.ru.volga.SpringShop11.dto.BucketDetailDTO;
import com.ru.volga.SpringShop11.service.BucketService;
import com.ru.volga.SpringShop11.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class BucketController {

    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }


    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal){
        if(principal == null){
            model.addAttribute("bucket", new BucketDTO());
        }
        else {
            BucketDTO bucketDTO = bucketService.getBucketDTOByUser(principal.getName());
            Bucket bucket = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucketDTO", bucketDTO);
            model.addAttribute("bucket", bucket);
        }
        return "bucket";
    }

    @GetMapping("/remove")
    public String deleteProduct(Model model, @RequestParam Long bucketId, @RequestParam Long productId){
        model.addAttribute("productId", productId);
        model.addAttribute("bucketId", bucketId);
        return "bucket";
    }
    @Transactional
    @PostMapping("/remove")
    public String deleteOneProduct(@RequestParam("productId") Long productId, @RequestParam Long bucketId) {
        bucketService.deleteOneProduct(bucketId, productId);
        return "redirect:/bucket";
    }
}
