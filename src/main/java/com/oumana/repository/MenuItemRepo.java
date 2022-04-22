package com.oumana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.MenuItem;

public interface MenuItemRepo extends JpaRepository<MenuItem, Long>{

}
