package com.oumana.payload;

import java.time.LocalDate;


public class ItemReviewDto {
	private Long id;
	private String text;
	private String user;
	private LocalDate creationDate;
	
	public ItemReviewDto() {
		super();
	}

	public ItemReviewDto(String text, String user, LocalDate creationDate, Long id) {
		super();
		this.text = text;
		this.user = user;
		this.creationDate = creationDate;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
