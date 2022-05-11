package com.zensar.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.security.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	List<UserEntity> findByUsername(String username);
}
