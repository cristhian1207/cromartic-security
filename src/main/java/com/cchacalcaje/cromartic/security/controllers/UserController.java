package com.cchacalcaje.cromartic.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cchacalcaje.cromartic.security.entity.User;
import com.cchacalcaje.cromartic.security.service.IUserService;

@RestController
@RequestMapping(value="users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("")
	public List<User> findAll(){
		return userService.findAll();
	}
	
	@GetMapping("/{username}")
	public User findByUsername(@PathVariable(name="username") String username) {
		return userService.findByUsername(username);
	}
	
	@PostMapping("/login")
	public User findByUsernameAndPassword(@RequestBody User user) {
		return userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
