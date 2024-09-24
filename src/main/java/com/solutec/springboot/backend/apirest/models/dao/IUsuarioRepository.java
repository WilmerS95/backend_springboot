package com.solutec.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.solutec.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);

}
