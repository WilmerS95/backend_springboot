package com.solutec.springboot.backend.apirest.auth;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.solutec.springboot.backend.apirest.jwt.JwtAuthenticationFilter;

//import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig /* implements WebSecurityConfigurer */    {
	
	/*
	 * @Autowired private UserDetailsService usuarioService;
	 * 
	 * @Override public void init(SecurityBuilder builder) throws Exception { //
	 * TODO Auto-generated method stub
	 * 
	 * }
	 * 
	 * @Override public void configure(SecurityBuilder builder) throws Exception {
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 */

	

	
	
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .authorizeHttpRequests((authorize) -> authorize
	 * .anyRequest().authenticated() ) .oauth2ResourceServer((oauth2) -> oauth2
	 * .jwt(Customizer.withDefaults()) ); return http.build(); }
	 */
		/*
		 * @Bean public JwtDecoder jwtDecoder() { return
		 * JwtDecoders.fromIssuerLocation("https://my-auth-server.com"); }
		 */
	
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	private AuthenticationProvider authProvider;
	 
	public SecurityFilterChain securitiyFilterChain(HttpSecurity http) throws Exception  //Throws Exception es autoimportado
	{
		return http
				.csrf(csrf -> //Proteccion CSRF ( se habilita por defecto en spring boot ) CSRF (Cross-Site Request Forgery) es un tipo de protección que impide que un sitio web pudiera hacer peticiones maliciosas a otro sitio web que fuese seguro, en caso de tener ambos abiertos a la vez en dos pestañas diferentes del navegador, y nos hubiésemos autenticado el segundo.
					csrf
					.disable())
			.authorizeHttpRequests(authRequest ->
				authRequest
					.requestMatchers("/api/**").permitAll()//Permite el acceso a cualquier endpoint con la ruta que se especifica aqui
					.anyRequest().authenticated()//Cualquier otro request se pide que se autentique
					)
				.sessionManagement(
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				// .formLogin(withDefaults())
				.build();
	}

}
