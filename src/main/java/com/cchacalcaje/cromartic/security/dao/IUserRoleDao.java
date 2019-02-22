package com.cchacalcaje.cromartic.security.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cchacalcaje.cromartic.security.entity.UserRole;
import com.cchacalcaje.cromartic.security.entity.key.UserRoleKey;

public interface IUserRoleDao extends JpaRepository<UserRole, UserRoleKey> {
	
}
