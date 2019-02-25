package com.cchacalcaje.cromartic.security.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchacalcaje.cromartic.security.entity.User;

public interface IUserDao extends JpaRepository<User, Long>{

	public List<User> findByStatus(Boolean status);
	public User findByUsernameAndStatus(String username, Boolean status);	
	
}
