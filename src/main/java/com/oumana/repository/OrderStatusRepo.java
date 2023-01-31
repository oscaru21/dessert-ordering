package com.oumana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.OrderStatus;
import com.oumana.entity.User;

public interface OrderStatusRepo extends JpaRepository<OrderStatus, Long>{
	Optional<OrderStatus> findByName(String name);
}
