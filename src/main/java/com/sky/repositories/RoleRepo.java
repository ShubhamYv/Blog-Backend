package com.sky.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sky.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
