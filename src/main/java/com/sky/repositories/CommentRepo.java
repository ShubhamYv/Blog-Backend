package com.sky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
