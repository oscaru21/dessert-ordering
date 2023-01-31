package com.oumana.service;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oumana.entity.MenuItem;
import com.oumana.entity.Order;
import com.oumana.entity.OrderItem;
import com.oumana.payload.OrderItemDto;
import com.oumana.repository.MenuItemRepo;
import com.oumana.repository.OrderItemRepo;
import com.oumana.repository.OrderRepo;

@Service
public class OrderItemService {
	@Autowired
	private OrderItemRepo orderItemRepo;
	@Autowired
	private MenuItemRepo menuItemRepo;
	@Autowired
	private OrderService orderService;

	
	public OrderItem createOrderItem(OrderItemDto orderItemDto) {
		MenuItem menuItem = menuItemRepo.findById(orderItemDto.getMenuItemId()).get();
		Order order = orderService.getOrder(orderItemDto.getOrderId());
		OrderItem orderItem = new OrderItem();
		
		orderItem.setMenuItem(menuItem);
		orderItem.setOrder(order);
		orderItem.setQuantity(orderItemDto.getQuantity());

		return orderItemRepo.save(orderItem);
	}
}
