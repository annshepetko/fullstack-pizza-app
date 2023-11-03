package com.example.annfullstack.models.Order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "products")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String title;
    private  String description;
    private Integer quantity;
    private Integer price;


}