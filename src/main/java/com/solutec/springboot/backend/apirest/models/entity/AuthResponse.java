package com.solutec.springboot.backend.apirest.models.entity;

import lombok.*;

@Data
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	String token;
}
