package com.example.annfullstack.controllers;

import com.example.annfullstack.models.Product;
import com.example.annfullstack.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/pizza")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

}
