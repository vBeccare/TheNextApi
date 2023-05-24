package com.theNext.backend.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.theNext.backend.model.Usuario;
import com.theNext.backend.model.UsuarioLogin;
import com.theNext.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
			return Optional.empty();

		usuario.setPassword(criptografarSenha(usuario.getPassword()));

		return Optional.of(usuarioRepository.save(usuario));

	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		String email = usuario.getEmail();
		Usuario usuarioBD = usuarioRepository.findByEmail(email).get();
		if (usuarioRepository.findByEmail(email).isPresent()) {
			if (usuario.getPassword() != null) {
				usuarioBD.setPassword(criptografarSenha(usuario.getPassword()));
			}

			if (usuario.getGrupo() != 0) {
				usuarioBD.setGrupo(usuario.getGrupo());
			}
			usuarioBD.setName(usuario.getName());

			usuarioRepository.saveAndFlush(usuarioBD);
			return Optional.of(usuarioBD);
		}

		return Optional.empty();

	}

	public Optional<Usuario> atualizarStatus(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email).get();

		if (usuarioRepository.findByEmail(email).isPresent()) {

			if (usuario.isAtivo()) {
				usuario.setAtivo(false);
				usuarioRepository.saveAndFlush(usuario);
				return Optional.of(usuario);
			} else {
				usuario.setAtivo(true);
				usuarioRepository.saveAndFlush(usuario);
				return Optional.of(usuario);
			}

		}

		return Optional.empty();

	}

	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioLogin.get().getEmail());

		if (usuario.isPresent()) {
			if (compararSenhas(usuarioLogin.get().getPassword(), usuario.get().getPassword())) {

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setName(usuario.get().getName());
				usuarioLogin.get().setEmail(usuario.get().getEmail());
				usuarioLogin.get().setCpf(usuario.get().getCpf());
				usuarioLogin.get().setGrupo(usuario.get().getGrupo());
				usuarioLogin.get()
						.setToken(gerarBasicToken(usuarioLogin.get().getEmail(), usuarioLogin.get().getPassword()));
				usuarioLogin.get().setPassword(usuario.get().getPassword());

				return usuarioLogin;

			}
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

	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}
