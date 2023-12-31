package com.sky.services;

import java.util.List;
import com.sky.payloads.PostDto;
import com.sky.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete
	void deletePost(Integer postId);

	// get post by id
	PostDto getPostById(Integer postId);

	// get all
	PostResponse getAllPosts(Integer pageNum, Integer pageSize, String sortBy, String sortDir);

	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);

	// get all post by User
	List<PostDto> getPostByUser(Integer userId);

	// search post
	List<PostDto> searchPosts(String keyword);
}
