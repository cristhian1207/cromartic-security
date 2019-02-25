package com.cchacalcaje.cromartic.security.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.cchacalcaje.cromartic.security.utils.FilterFields;

@RestController
@RequestMapping(value="users")
@CrossOrigin(origins= {"http://localhost:4200"})
public class UserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	private FilterFields filterFields;
	
	private static final List<String> USER_FIELDS = Arrays.asList("username", "name", "pat_lastname", "mat_lastname",
			"email", "image", "created_date", "updated_date");
	
	@GetMapping("")
	public MappingJacksonValue findAll(){
		return filterFields.filterEntity(userService.findAll(), "user_filter", USER_FIELDS);
	}
	
	@GetMapping("/{username}")
	public MappingJacksonValue findByUsername(@PathVariable String username) {	
		User user = userService.findByUsername(username);
		if(user == null)
			throw new ResourceNotFoundException("user");		
		return filterFields.filterEntity(user, "user_filter", USER_FIELDS);
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
