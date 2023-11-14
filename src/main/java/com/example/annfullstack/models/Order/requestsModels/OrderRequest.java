package com.example.annfullstack.models.Order.requestsModels;

import com.example.annfullstack.models.Order.Product;
import com.example.annfullstack.models.user.User;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    private String firstname;
    private String lastname;
    @Nullable
    private String token;
    private String phoneNumber;
    private Integer netWorth;
    private List<Product> orderBucket;

}
