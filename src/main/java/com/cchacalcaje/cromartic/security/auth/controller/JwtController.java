package com.cchacalcaje.cromartic.security.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cchacalcaje.cromartic.security.auth.entity.Token;
import com.cchacalcaje.cromartic.security.auth.service.IJwtService;
import com.cchacalcaje.cromartic.security.exception.TokenInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@CrossOrigin(origins= {"http://localhost:4200"})
public class JwtController {

	@Autowired
	private IJwtService jwtService;
	
	@PostMapping("/refresh-token")
	public Token refreshToken(@RequestHeader("Authorization") String authorization) {
		if(!jwtService.validate(authorization))
			throw new TokenInvalidException();
		try {
			return new Token(jwtService.refreshToken(authorization));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new Token("");
		}
	}
}
