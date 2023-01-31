package com.oumana.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oumana.entity.ItemReview;
import com.oumana.payload.ItemReviewDto;
import com.oumana.service.ItemReviewService;

@RestController
@RequestMapping("/api/menu-items/{menuItemId}/item-reviews")
public class ItemReviewController {
	
	@Autowired
	private ItemReviewService itemReviewService;
	
	@GetMapping
	public ResponseEntity<List<ItemReviewDto>> getItemReviews(@PathVariable Long menuItemId) {
		List<ItemReview> itemReviews = itemReviewService.getItemReviews(menuItemId);
		List<ItemReviewDto> response = itemReviews.stream().map(review -> mapEntityToDto(review)).collect(Collectors.toList());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ItemReviewDto> createItemReview(@PathVariable Long menuItemId,
			@RequestBody ItemReview itemReview) {
		ItemReview createItemReview = itemReviewService.createItemReview(menuItemId, itemReview);
		return new ResponseEntity<>(mapEntityToDto(createItemReview), HttpStatus.CREATED);
	}
	
	public ItemReviewDto mapEntityToDto(ItemReview i) {
		ItemReviewDto itemReviewDto = new ItemReviewDto();
		itemReviewDto.setId(i.getId());
		itemReviewDto.setText(i.getText());
		itemReviewDto.setCreationDate(i.getCreationDate());
		itemReviewDto.setUser(i.getUser().getUsername());
		return itemReviewDto;
	}
}
