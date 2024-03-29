package com.oumana.payload;

public class UserDto {
	private String name;
	private String username;
	private String email;
	private String token;
	private Boolean isAdmin;
	
	public UserDto() {
		super();
	}

	public UserDto(String name, String username, String email, String token, Boolean isAdmin) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.token = token;
		this.isAdmin = isAdmin;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
