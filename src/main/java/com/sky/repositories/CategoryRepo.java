package com.sky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
