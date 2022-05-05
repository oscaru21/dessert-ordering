package com.oumana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.entity.Order;
import com.oumana.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> getAll(){
		return null;
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
		return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order orderData){
		Order order = orderService.createOrder(orderData);
		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}
	

}
