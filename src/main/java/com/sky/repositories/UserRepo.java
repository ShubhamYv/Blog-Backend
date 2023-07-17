package com.sky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
