package com.sky.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.entities.Category;
import com.sky.entities.Post;
import com.sky.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
}
