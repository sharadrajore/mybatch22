package com.zensar.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
	@Id
	private int id;
	private String username;
	private String password;
	private String roles;

}
