package com.oumana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{

}
