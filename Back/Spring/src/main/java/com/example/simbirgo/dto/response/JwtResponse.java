package com.example.simbirgo.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;

	public JwtResponse(String token, Long id, String email, List<String> roles) {
		this.token = token;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
	private List<String> roles;
}
