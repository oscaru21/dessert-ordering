package com.oumana.payload;

import java.math.BigInteger;

public class OrderItemDto {
	private Long id;
	private BigInteger quantity;
	private Long menuItemId;
	private Long orderId;
	
	public OrderItemDto(Long id, BigInteger quantity, Long menuItemId, Long orderId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.menuItemId = menuItemId;
		this.orderId = orderId;
	}
	public OrderItemDto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigInteger getQuantity() {
		return quantity;
	}
	public void setQuantity(BigInteger quantity) {
		this.quantity = quantity;
	}
	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
