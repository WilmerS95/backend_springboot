package com.solutec.springboot.backend.apirest.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solutec.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//public Usuario findByUsername(String username);
	
	Optional<Usuario> findByUsername(String username);

}
