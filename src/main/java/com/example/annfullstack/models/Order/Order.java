package com.example.annfullstack.models.Order;

import com.example.annfullstack.models.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity(name = "orders")
@Data

public class Order {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long order_id;

    @ManyToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> products;
    private Integer netWorth;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Nullable
    private User user;

    private String phoneNumber;

}
