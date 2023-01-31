package com.oumana.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.entity.OrderItem;
import com.oumana.payload.OrderItemDto;
import com.oumana.service.OrderItemService;

@RestController
@RequestMapping("/api/orders/{orderId}")
public class OrderItemController {
	@Autowired
	private OrderItemService orderItemService;
	@PostMapping
	public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItem, @PathVariable Long orderId){
		OrderItem savedOrderItem = orderItemService.createOrderItem(orderItem);
		orderItem.setId(savedOrderItem.getId());
		return new ResponseEntity<>(orderItem,HttpStatus.CREATED);
	}
}
