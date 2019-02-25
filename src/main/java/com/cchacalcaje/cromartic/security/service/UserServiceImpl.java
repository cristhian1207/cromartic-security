package com.cchacalcaje.cromartic.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cchacalcaje.cromartic.security.dao.IUserDao;
import com.cchacalcaje.cromartic.security.entity.Role;
import com.cchacalcaje.cromartic.security.entity.User;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional
	public User save(User user) {
		user.setPassword(encriptPassword(user.getPassword()));
		return userDao.save(user);
	}	
	
	@Override
	@Transactional
	public User update(User user) {
		return userDao.save(user);
	}
		
	@Override
	@Transactional
	public void delete(User user) {
		user.setStatus(false);
		userDao.save(user);
	}
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		User user = findByUsername(username);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		org.springframework.security.core.userdetails.User springUser = new 
				org.springframework.security.core.userdetails.User(user.getUsername(),
						user.getPassword(), user.getStatus(), true, true, true, authorities);
		return springUser;
	}

	private String encriptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

}
