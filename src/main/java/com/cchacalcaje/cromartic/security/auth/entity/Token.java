package com.cchacalcaje.cromartic.security.auth.entity;

public class Token {
	private String token;

	public Token() { }

	public Token(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Token [token=" + token + "]";
	}
}
