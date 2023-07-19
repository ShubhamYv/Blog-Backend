package com.sky.services;

import com.sky.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment(Integer commentId);
	
	// Update
}
