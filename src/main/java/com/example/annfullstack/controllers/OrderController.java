package com.example.annfullstack.controllers;

import com.example.annfullstack.models.Order.requestsModels.OrderRequest;
import com.example.annfullstack.repository.OrderRepository;
import com.example.annfullstack.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/order")
    public void saveOrder(@RequestBody OrderRequest orderRequest) {

        orderService.saveOrder(orderRequest);
    }

}