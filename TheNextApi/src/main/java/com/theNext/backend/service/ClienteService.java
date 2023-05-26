package com.theNext.backend.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.theNext.backend.model.Cliente;
import com.theNext.backend.model.ClienteLogin;
import com.theNext.backend.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public Optional<Cliente> cadastrarCliente(Cliente cliente) {

		if (clienteRepository.findByEmail(cliente.getEmail()).isPresent())
			return Optional.empty();

		cliente.setPassword(criptografarSenha(cliente.getPassword()));

		return Optional.of(clienteRepository.save(cliente));

	}

	public Optional<Cliente> atualizarCliente(Cliente cliente) {
		String email = cliente.getEmail();
		Cliente clienteBD = clienteRepository.findByEmail(email).get();
		if (clienteRepository.findByEmail(email).isPresent()) {
			if (cliente.getPassword() != null) {
				clienteBD.setPassword(criptografarSenha(cliente.getPassword()));
			}
			if (cliente.getName() != null) {
				clienteBD.setName(cliente.getName());
			}
			if (cliente.getDataNascimento() != null) {
				clienteBD.setDataNascimento(cliente.getDataNascimento());
			}
			if (cliente.getGenero() != null) {
				clienteBD.setGenero(cliente.getGenero());
			}
			if (cliente.getCpf() != null) {
				clienteBD.setCpf(cliente.getCpf());
			}

			clienteRepository.saveAndFlush(clienteBD);
			return Optional.of(clienteBD);
		}

		return Optional.empty();

	}

	public Optional<ClienteLogin> autenticarCliente(Optional<ClienteLogin> clienteLogin) {

		Optional<Cliente> cliente = clienteRepository.findByEmail(clienteLogin.get().getEmail());

		if (cliente.isPresent()) {
			if (compararSenhas(clienteLogin.get().getPassword(), cliente.get().getPassword())) {

				clienteLogin.get().setId(cliente.get().getId());
				clienteLogin.get().setName(cliente.get().getName());
				clienteLogin.get().setEmail(cliente.get().getEmail());
				clienteLogin.get().setDataNascimento(cliente.get().getDataNascimento());
				clienteLogin.get().setGenero(cliente.get().getGenero());
				clienteLogin.get().setCpf(cliente.get().getCpf());
				clienteLogin.get().setCpf(cliente.get().getCpf());
				clienteLogin.get()
						.setToken(gerarBasicToken(clienteLogin.get().getEmail(), clienteLogin.get().getPassword()));
				clienteLogin.get().setPassword(cliente.get().getPassword());

				return clienteLogin;

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

	private String gerarBasicToken(String cliente, String senha) {

		String token = cliente + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}
