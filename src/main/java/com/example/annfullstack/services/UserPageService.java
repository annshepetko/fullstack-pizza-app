package com.example.annfullstack.services;

import com.example.annfullstack.controllers.authControllers.oauth.response_models.UserCredentials;
import com.example.annfullstack.controllers.authControllers.oauth.services.GoogleAccessService;
import com.example.annfullstack.models.combined.UserOrdersFullInfo;
import com.example.annfullstack.models.user.User;
import com.example.annfullstack.models.user.UserPageDetails;
import com.example.annfullstack.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserPageService {
    private final JwtService jwtService;
    private final OrderRepository orderRepository;
    private final GoogleAccessService googleAccessService;
    public UserPageDetails getUserDetails(String token) throws URISyntaxException, IOException, InterruptedException {

        User user = jwtService.exchangeTokenForUser(token);
        List<UserOrdersFullInfo> ordersUser = orderRepository.getUserOrdersFullInfo(user.getUser_id());
       ;

        HashSet<UserOrdersFullInfo> uniqueOrders = new HashSet<>(ordersUser);

        List<UserOrdersFullInfo> listUniqueOrders = new ArrayList<>(uniqueOrders);
        Integer generalExpenses = listUniqueOrders.stream()
                .map(elem -> elem.getNetWorth())
                .reduce(0, (iterator, accumulator) -> iterator + accumulator);

        return UserPageDetails.builder()
                .firstname(user.getFirstname())
                .orders(ordersUser)
                .lastname(user.getLastname())
                .generalExpenses(generalExpenses)
                .build();
    }
}
