package com.solutec.springboot.backend.apirest.models.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	String username;
	String password;
	String firstName;
	String lastName;
	String country;
}
