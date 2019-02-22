package com.cchacalcaje.cromartic.security.service;

import java.util.List;

import com.cchacalcaje.cromartic.security.entity.User;

public interface IUserService {

	public List<User> findAll();
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	
}
