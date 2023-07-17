package com.sky.services;

import java.util.List;

import com.sky.payloads.CategoryDto;

public interface CategoryService {

	// create
	CategoryDto createCategory(CategoryDto categoryDto);

	// update
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	void deleteCategory(Integer categoryId);

	// get single
	CategoryDto getSingleCategory(Integer id);

	// get all
	List<CategoryDto> getAllCategory();
}
