package com.theNext.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theNext.backend.model.Usuario;
import com.theNext.backend.model.UsuarioLogin;
import com.theNext.backend.repository.UsuarioRepository;
import com.theNext.backend.service.UsuarioService;





@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Usuario>> getAll(){
		
		return ResponseEntity.ok(usuarioRepository.findAll());
		
	}

	@GetMapping("/nome/{name}")
	public ResponseEntity<List<Usuario>> getAll(@PathVariable String name) {
		return ResponseEntity.ok(usuarioRepository.findAllByNameContainingIgnoreCase(name)); 
																									
	}

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> email) {
		return usuarioService.autenticarUsuario(email)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario email) {

		return usuarioService.cadastrarUsuario(email)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario email) {
		return usuarioService.atualizarUsuario(email)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

}

