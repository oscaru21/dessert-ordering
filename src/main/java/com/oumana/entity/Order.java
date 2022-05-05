package com.oumana.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"user", "orderItems"})
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String Address;
	
	@Column(nullable = false)
	private LocalDate creationDate;
	
	private String Note;
	
	@Column(nullable = false)
	private BigDecimal price = new BigDecimal(0);
	
	@Column(columnDefinition = "boolean default false", nullable = false)
	private Boolean isPaid = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_order_status_id", nullable = false)
	private OrderStatus orderStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
	private Set<OrderItem> orderItems;
	

	public Order() {
		super();
	}
	
	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	public Order(Long id, String address, LocalDate creationDate, String note, BigDecimal price, Boolean isPaid,
			OrderStatus orderStatus, User user) {
		super();
		this.id = id;
		Address = address;
		this.creationDate = creationDate;
		Note = note;
		this.price = price;
		this.isPaid = isPaid;
		this.orderStatus = orderStatus;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
