package com.example.annfullstack.repository;

import com.example.annfullstack.models.Order.Order;
import com.example.annfullstack.models.combined.UserOrdersFullInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = "SELECT " +
            "u.user_id AS user_id, " +
            "u.firstname AS username, " +
            "u.lastname, " +
            "o.order_id AS order_id, " +
            "o.net_worth, " +
            "p.id AS product_id, " +
            "p.title, " +
            "p.description, " +
            "p.quantity, " +
            "p.price " +
            "FROM " +
            "users u " +
            "JOIN " +
            "orders o ON u.user_id = o.id_user " +
            "JOIN " +
            "orders_products op ON o.order_id = op.orders_order_id " +
            "JOIN " +
            "products p ON op.products_id = p.id " +
            "WHERE " +
            "u.user_id = :userId")
    List<UserOrdersFullInfo> getUserOrdersFullInfo(@Param("userId") Long userId);

}
