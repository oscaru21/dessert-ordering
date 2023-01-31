package com.oumana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.oumana.entity.ItemReview;

public interface ItemReviewRepo extends JpaRepository<ItemReview, Long>{
	@Query("select r from ItemReview r where r.menuItem.id = ?1")
	List<ItemReview> findByMenuItemId(Long menuItemId);
}
