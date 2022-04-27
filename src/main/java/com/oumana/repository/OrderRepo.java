package com.oumana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

}
