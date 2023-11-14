package com.example.annfullstack.models.combined;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Objects;

@Data
public class UserOrdersFullInfo  {

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

    @Override
    public boolean equals( Object userOrdersFullInfo ){
        if(userOrdersFullInfo instanceof  UserOrdersFullInfo){
            UserOrdersFullInfo userOrdersFullInfo1 = (UserOrdersFullInfo) userOrdersFullInfo;
            if (userOrdersFullInfo1.getOrder_id() == this.order_id){
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return Objects.hash(order_id);
    }
    public UserOrdersFullInfo(Long userId, String username, String lastname, Long orderId, Integer netWorth, Long productId, String title, String description, Integer quantity, Integer price) {
        this.user_id = userId;
        this.username = username;
        this.lastname = lastname;
        this.order_id = orderId;
        this.netWorth = netWorth;
        this.id = productId;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

}