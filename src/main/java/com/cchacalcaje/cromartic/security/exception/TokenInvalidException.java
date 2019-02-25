package com.cchacalcaje.cromartic.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TokenInvalidException() {
		super("Invalid Token.");
	}

}
