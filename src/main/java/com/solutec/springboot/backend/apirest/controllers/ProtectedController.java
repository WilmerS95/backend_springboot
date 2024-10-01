package com.solutec.springboot.backend.apirest.controllers;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProtectedController {

	@PostMapping(value = "demo")
	public String welcome(){
		return "Welcome Form Secury Endpoint";
	}
}
