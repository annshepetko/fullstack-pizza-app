package com.example.annfullstack.repository;

import com.example.annfullstack.models.Order.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
