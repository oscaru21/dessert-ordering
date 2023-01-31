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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.entity.MenuItem;
import com.oumana.service.MenuItemService;

@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {
	
	@Autowired
	private MenuItemService menuItemService;
	
	@PostMapping()
	public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
		MenuItem response = menuItemService.createMenuItem(menuItem);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<MenuItem>> getMenuItems(){
		List<MenuItem> menuItems = menuItemService.getMenuItems();
		return new ResponseEntity<>(menuItems, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id){
		MenuItem menuItem = menuItemService.getMenuItem(id);
		return new ResponseEntity<>(menuItem, HttpStatus.OK);
	}
	
}
