package com.sky.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
