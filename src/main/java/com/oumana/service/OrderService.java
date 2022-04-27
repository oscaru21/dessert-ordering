package com.oumana.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oumana.entity.Order;
import com.oumana.entity.OrderStatus;
import com.oumana.entity.User;
import com.oumana.repository.OrderRepo;
import com.oumana.repository.OrderStatusRepo;
import com.oumana.repository.UserRepo;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private OrderStatusRepo orderStatusRepo;
	@Autowired
	private UserRepo userRepo;

	public Order createOrder(Order orderData) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		orderData.setUser(user);
		OrderStatus orderStatus = orderStatusRepo.findByName("CREATED").orElseThrow(() -> new RuntimeException("Order status not valid"));
		orderData.setOrderStatus(orderStatus);
		orderData.setCreationDate(LocalDate.now());
		return orderRepo.save(orderData);
	}

}
