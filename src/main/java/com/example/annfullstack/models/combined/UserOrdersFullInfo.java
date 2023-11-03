package com.example.annfullstack.models.combined;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserOrdersFullInfo {

    private Long user_id;
    private String username;
    private String lastname;

    private Long order_id;
    private Integer netWorth;

    @Column(name = "product_id")
    private Long id;
    private  String title;
    private  String description;
    private Integer quantity;
    private Integer price;
}