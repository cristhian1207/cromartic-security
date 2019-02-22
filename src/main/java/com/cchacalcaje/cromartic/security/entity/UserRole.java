package com.cchacalcaje.cromartic.security.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cchacalcaje.cromartic.security.entity.key.UserRoleKey;

@Entity
@Table(name="tb_user_role")
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private UserRoleKey userRoleKey;

	protected UserRole() { }

	public UserRole(UserRoleKey userRoleKey) {
		super();
		this.userRoleKey = userRoleKey;
	}

	public UserRoleKey getUserRoleKey() {
		return userRoleKey;
	}

	public void setUserRoleKey(UserRoleKey userRoleKey) {
		this.userRoleKey = userRoleKey;
	}

	@Override
	public String toString() {
		return "UserRole [userRoleKey=" + userRoleKey + "]";
	}
		
}
