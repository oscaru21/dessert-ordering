package com.oumana.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oumana.entity.ItemReview;
import com.oumana.entity.MenuItem;
import com.oumana.entity.User;
import com.oumana.repository.ItemReviewRepo;
import com.oumana.repository.MenuItemRepo;

@Service
public class ItemReviewService {
	
	@Autowired
	private ItemReviewRepo itemReviewRepo;
	
	@Autowired
	private MenuItemRepo menuItemRepo;

	public List<ItemReview> getItemReviews(Long menuItemId) {
		return itemReviewRepo.findByMenuItemId(menuItemId);
	}

	public ItemReview createItemReview(Long menuItemId, ItemReview itemReview) {
		MenuItem menuItem = menuItemRepo.findById(menuItemId).orElseThrow(() -> new RuntimeException("Resource not found"));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		itemReview.setMenuItem(menuItem);
		itemReview.setUser(user);
		itemReview.setCreationDate(LocalDate.now());
		return itemReviewRepo.save(itemReview);
	}

}
