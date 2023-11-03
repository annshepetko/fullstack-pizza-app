package com.example.annfullstack.controllers;

import com.example.annfullstack.models.Order.Order;
import com.example.annfullstack.models.Order.OrderProduct;
import com.example.annfullstack.models.Order.Product;
import com.example.annfullstack.models.requests.OrderRequest;
import com.example.annfullstack.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    @PostMapping("/order")
    public void saveOrder(@RequestBody OrderRequest orderRequest) {
        Order order = new Order();

        List<OrderProduct> orderProducts = new ArrayList<>();

        for ( Product product : orderRequest.getOrderBucket() ){
            OrderProduct orderProduct = new OrderProduct();

            orderProduct.setQuantity(product.getQuantity());
            orderProduct.setOrder(order);
            orderProduct.setProduct_id(product);
            orderProducts.add(orderProduct);
        }
        order.setUser(orderRequest.getUser());
        order.setNetWorth(orderRequest.getNetWorth());
        order.setProducts(orderProducts);

        orderRepository.save(order);
    }
}