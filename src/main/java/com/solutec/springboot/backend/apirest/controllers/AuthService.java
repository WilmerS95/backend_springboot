package com.solutec.springboot.backend.apirest.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.solutec.springboot.backend.apirest.models.dao.IUsuarioRepository;
import com.solutec.springboot.backend.apirest.models.entity.AuthResponse;
import com.solutec.springboot.backend.apirest.models.entity.LoginRequest;
import com.solutec.springboot.backend.apirest.models.entity.RegisterRequest;
import com.solutec.springboot.backend.apirest.models.entity.Roles;
import com.solutec.springboot.backend.apirest.models.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private IUsuarioRepository userRepository;
	private JwtService jwtService;
	private PasswordEncoder passwordEncoder;

	public AuthResponse login(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public AuthResponse register(RegisterRequest request) {
		Usuario usuario = Usuario.builder()
				.username(request.getUsername())
				.password(request.getPassword())
				.firstname(request.getFirstName())
				.lastname(request.getLastName())
				.country(request.getCountry())
				.role(Roles.USER)
				.build();
		
		userRepository.save(usuario);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(usuario))
				.build();
	}

}
