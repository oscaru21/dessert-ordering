package com.oumana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oumana.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
