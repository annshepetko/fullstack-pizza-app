package com.example.annfullstack.models.Order;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "orders_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_products_id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "product_id")
    private Product product_id;
}
