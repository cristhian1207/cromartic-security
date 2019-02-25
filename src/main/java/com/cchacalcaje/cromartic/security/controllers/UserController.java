package com.cchacalcaje.cromartic.security.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cchacalcaje.cromartic.security.entity.User;
import com.cchacalcaje.cromartic.security.exception.ResourceNotFoundException;
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
	public User findByUsername(@PathVariable String username) {	
		User user = userService.findByUsername(username);
		if(user == null)
			throw new ResourceNotFoundException("user");
		return user;
	}
	
	@PostMapping("")
	public ResponseEntity<Object> save(@RequestBody User user) {
		User savedUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{username}").buildAndExpand(savedUser.getUsername()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{username}")
	public User update(@PathVariable String username, @RequestBody User user) {
		if(userService.findByUsername(username) == null)
			throw new ResourceNotFoundException("user");		
		return userService.update(user);
	}
	
	@DeleteMapping("/{username}")
	public void delete(@PathVariable String username) {
		User user = userService.findByUsername(username);
		if(user == null)
			throw new ResourceNotFoundException("user");
		userService.delete(user);
	}
}
