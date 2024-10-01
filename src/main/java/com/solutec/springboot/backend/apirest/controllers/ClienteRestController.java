package com.solutec.springboot.backend.apirest.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.solutec.springboot.backend.apirest.models.entity.AuthResponse;
import com.solutec.springboot.backend.apirest.models.entity.Cliente;
import com.solutec.springboot.backend.apirest.models.entity.LoginRequest;
import com.solutec.springboot.backend.apirest.models.entity.RegisterRequest;
import com.solutec.springboot.backend.apirest.models.services.IClienteService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins= {"*"})
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ClienteRestController {
	
	private final AuthService authService = new AuthService();
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put("mensaje", "El cliente id:".concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		//return clienteService.findById(id);	
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Cliente cliente) {
		//cliente.setCreateAt(new Date()); //Se agrego en la clase entity
		//return clienteService.save(cliente);
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			clienteNew = clienteService.save(cliente);			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al guardar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "El cliente ha sido guardado exitosamente.");
		response.put("Cliente:", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		if(clienteActual == null) {
			response.put("Error", "No se puede editar el cliente id:".concat(id.toString().concat(" No existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteUpdated = clienteService.save(clienteActual);				
		} catch(DataAccessException e) {
			response.put("Error", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "El cliente ha sido actualizado exitosamente.");
		response.put("Cliente:", clienteUpdated);
		//return clienteService.save(clienteActual);
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?>  delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		//clienteService.delete(id);
		try {
			clienteService.delete(id);
		}catch(DataAccessException e) {
			response.put("Error", "Error al eliminar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "El cliente id:".concat(id.toString().concat(" ha sido eliminado exitosamente")));
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}


}
