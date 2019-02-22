package com.cchacalcaje.cromartic.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cchacalcaje.cromartic.security.entity.Role;

public interface IRoleDao extends JpaRepository<Role, Long>{

	public Role findByRolename(String rolename);
	
}
