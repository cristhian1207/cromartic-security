package com.cchacalcaje.cromartic.security.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cchacalcaje.cromartic.security.entity.User;

public interface IUserDao extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
}
