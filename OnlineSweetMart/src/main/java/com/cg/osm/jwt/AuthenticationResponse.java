package com.cg.osm.jwt;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

	private final String jwt;
	private final String role;
	private final String userid;
	private final String username;
	private final String cartId;

	public AuthenticationResponse(String jwt, String role, String userid, String username, String cartId) {
		this.jwt = jwt;
		this.role = role;
		this.userid = userid;
		this.cartId = cartId;
		this.username = username;
	}

	public String getCartId() {
		return cartId;
	}

	public String getUsername() {
		return username;
	}

	public String getJwt() {
		return jwt;
	}

	public String getRole() {
		return role;
	}

	public String getUserid() {
		return userid;
	}

}
