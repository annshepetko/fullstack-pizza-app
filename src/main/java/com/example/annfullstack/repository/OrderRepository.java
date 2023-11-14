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

              @Query("SELECT new com.example.annfullstack.models.combined.UserOrdersFullInfo(u.user_id, u.firstname, u.lastname, o.order_id, o.netWorth, p.id, p.title, p.description, op.quantity, p.price) " +
                      "FROM orders o " +
                      "JOIN o.products op " +
                      "JOIN o.user u " +
                      "JOIN op.product_id p " +
                      "WHERE u.user_id = :userId")

       List<UserOrdersFullInfo> getUserOrdersFullInfo(@Param("userId") Long userId);

}
