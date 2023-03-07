package com.theNext.backend.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.theNext.backend.model.Usuario;
import com.theNext.backend.repository.UsuarioRepository;



@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario email){
		
		if (usuarioRepository.findByUsuario(email.getEmail()).isPresent())
			return Optional.empty();
 
			email.setPassword(criptografarSenha(email.getPassword()));

		return Optional.of(usuarioRepository.save(email));
	
	}

	public Optional<Usuario> atualizarUsuario(Usuario email) {

		
		if (usuarioRepository.findById(email.getId()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(email.getEmail());
			if(buscaUsuario.isPresent()) {
				if(buscaUsuario.get().getId()!= email.getId())
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"O email Cadastrado já existe !", null);
			}
			
			email.setPassword(criptografarSenha(email.getPassword()));
			
			return Optional.of(usuarioRepository.save(email));
			
		}
			
		return Optional.empty();

	}	


	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.encode(senha);

	}
	
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);

	}

	private String gerarBasicToken(String email, String senha) {

		String token = email + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}


	}
	
		
	
	

	
	

