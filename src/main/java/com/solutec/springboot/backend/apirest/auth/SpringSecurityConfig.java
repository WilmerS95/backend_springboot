package com.solutec.springboot.backend.apirest.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig  implements WebSecurityConfigurer    {
	
	@Autowired
	private UserDetailsService usuarioService;

	@Override
	public void init(SecurityBuilder builder) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(SecurityBuilder builder) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .authorizeHttpRequests((authorize) -> authorize
	 * .anyRequest().authenticated() ) .oauth2ResourceServer((oauth2) -> oauth2
	 * .jwt(Customizer.withDefaults()) ); return http.build(); }
	 * 
	 * @Bean public JwtDecoder jwtDecoder() { return
	 * JwtDecoders.fromIssuerLocation("https://my-auth-server.com"); }
	 */
}
