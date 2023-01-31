package com.oumana.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oumana.entity.MenuItem;
import com.oumana.repository.MenuItemRepo;

@Service
public class MenuItemService {
	@Autowired
	private MenuItemRepo menuItemRepo;
	
	public MenuItem createMenuItem(MenuItem menuItem) {
		return menuItemRepo.save(menuItem);
	}
	
	public List<MenuItem> getMenuItems(){
		return menuItemRepo.findAll();
	}

	public MenuItem getMenuItem(Long id) {
		MenuItem menuItem = menuItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Item Not Found"));
		return menuItem;
	}
}
