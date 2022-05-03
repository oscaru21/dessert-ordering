package com.oumana.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.payload.OrderItemDto;

@RestController
@RequestMapping("/api/orders/{orderId}")
public class OrderItemController {
	
	@PostMapping
	public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItem, @PathVariable Long orderId){
		return new ResponseEntity<>(orderItem,HttpStatus.CREATED);
	}
}
