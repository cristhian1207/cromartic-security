package com.cchacalcaje.cromartic.security.auth.service;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.cchacalcaje.cromartic.security.auth.SimpleGrantedAuthorityMixin;
import com.cchacalcaje.cromartic.security.entity.Role;
import com.cchacalcaje.cromartic.security.service.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements IJwtService {
		
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	private static final long expirationTime = 1000 * 60 * 60 * 24 * 7;
	
	private static final Key SECRET_KEY = new SecretKeySpec("RgUkXp2s5v8x/A?D(G+KbPeShVmYq3t6w9z$B&E)H@McQfTjWnZr4u7x!A%D*G-J".getBytes(),
			SignatureAlgorithm.HS512.getJcaName());
	
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public String create(Authentication auth) throws JsonProcessingException {	
		String username = ((User) auth.getPrincipal()).getUsername();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
						
		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(username)				
				.signWith(SECRET_KEY)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.compact();
		
		return token;
	}

	@Override
	public boolean validate(String token) {
		try {
			getClaims(token);
			return true;
		}catch(JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(resolve(token)).getBody();
		return claims;
	}

	@Override
	public String getUsername(Claims claims) {
		return claims.getSubject();
	}

	@Override
	public Collection<? extends GrantedAuthority> getRoles(Claims claims) throws IOException {
		Object roles = claims.get("authorities");
		Collection<? extends GrantedAuthority> authorities = 
			Arrays.asList(new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
					.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		return authorities;
	}

	@Override
	public String resolve(String token) {
		if((token != null) && (token.startsWith(TOKEN_PREFIX))) {
			return token.replace(TOKEN_PREFIX, "");
		}
		return null;
	}

	@Override
	public String refreshToken(String authorization) throws JsonProcessingException {		
		String username = getUsername(getClaims(authorization));
		List<GrantedAuthority> roles = new ArrayList<>();
		for(Role role: userService.findByUsername(username).getRoles()) {
			roles.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(username)				
				.signWith(SECRET_KEY)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expirationTime))
				.compact();
		return token;
	}

}
