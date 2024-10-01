package com.solutec.springboot.backend.apirest.controllers;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

	public Object getToken(UserDetails user) {
		// TODO Auto-generated method stub
		return getToken(new HashMap<>(), user);
	}

	private Object getToken(Map<String, Object> extraClaims, UserDetails user) {
		// TODO Auto-generated method stub
		return Jwts
				.builder()
				.setClaims(extraClaims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
