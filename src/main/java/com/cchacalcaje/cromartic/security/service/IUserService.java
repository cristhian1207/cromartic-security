package com.cchacalcaje.cromartic.security.service;

import java.util.List;

import com.cchacalcaje.cromartic.security.entity.User;

public interface IUserService {

	public List<User> findAll();
	public User findByUsername(String username);
	public User save(User user);
	public User update(User user);
	public void delete(User user);
}
