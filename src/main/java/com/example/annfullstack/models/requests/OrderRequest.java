package com.example.annfullstack.models.requests;

import com.example.annfullstack.models.Order.Product;
import com.example.annfullstack.models.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {

    private User user;

    private Integer netWorth;
    private List<Product> orderBucket;

}
