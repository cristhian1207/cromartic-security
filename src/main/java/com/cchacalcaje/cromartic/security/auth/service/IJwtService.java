package com.cchacalcaje.cromartic.security.auth.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.jsonwebtoken.Claims;

public interface IJwtService {

	public String create(Authentication auth) throws JsonProcessingException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String getUsername(Claims claims);
	public Collection<? extends GrantedAuthority> getRoles(Claims claims) throws IOException ;
	public String resolve(String token);
}
