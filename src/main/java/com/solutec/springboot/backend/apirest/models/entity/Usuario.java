package com.solutec.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements /* Serializable, */ UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(unique = true, length = 20, nullable = false)
	String username;

	@Column(length = 60)
	String password;
	Boolean enabled;

	@Enumerated(EnumType.STRING)
	Roles roles;
	//@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//private List<Role> roles;

	
	  public Long getId() { return id; }
	  
	  public void setId(Long id) { this.id = id; }
	  
	  public String getUsername() { return username; }
	  
	  public void setUsername(String username) { this.username = username; }
	  
	  public String getPassword() { return password; }
	  
	  public void setPassword(String password) { this.password = password; }
	  
	  public Boolean getEnabled() { return enabled; }
	  
	  public void setEnabled(Boolean enabled) { this.enabled = enabled; }
	 

	/*
	 * public List<Role> getRoles() { return roles; }
	 * 
	 * public void setRoles(List<Role> roles) { this.roles = roles; }
	 */

	private static final long serialVersionUID = 1L;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority((roles.name())));
	}

	
}
