package com.example.annfullstack.services;

import com.example.annfullstack.models.Order.Order;
import com.example.annfullstack.models.Order.OrderProduct;
import com.example.annfullstack.models.Order.Product;
import com.example.annfullstack.models.Order.requestsModels.OrderRequest;
import com.example.annfullstack.models.combined.UserOrdersFullInfo;
import com.example.annfullstack.models.user.User;
import com.example.annfullstack.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final JwtService jwtService;
    private final OrderRepository orderRepository;
    private UserDetailsService userDetailsService;
    public void saveOrder(OrderRequest orderRequest){

        Order order = buildOrder(orderRequest);

        orderRepository.save(order);
    }
    private Order buildOrder(OrderRequest orderRequest){
        Order order = new Order();
        List<OrderProduct> orderProducts = new ArrayList<>();

        for ( Product product : orderRequest.getOrderBucket() ){
            OrderProduct orderProduct = new OrderProduct();

            orderProduct.setQuantity(product.getQuantity());
            orderProduct.setOrder(order);
            orderProduct.setProduct_id(product);
            orderProducts.add(orderProduct);
        }
        if(orderRequest.getToken() != null) {
            User user = jwtService.exchangeTokenForUser(orderRequest.getToken());
            order.setUser(user);
        }

        order.setNetWorth(orderRequest.getNetWorth());
        order.setProducts(orderProducts);
        order.setPhoneNumber(orderRequest.getPhoneNumber());

        return order;
    }
}
