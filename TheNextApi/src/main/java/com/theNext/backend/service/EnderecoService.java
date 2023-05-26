package com.theNext.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theNext.backend.model.Endereco;
import com.theNext.backend.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Optional<Endereco> cadastrarEndereco(Endereco endereco) {

        return Optional.of(enderecoRepository.save(endereco));

    }

    public Optional<Endereco> atualizarEndereco(Endereco endereco) {
        Long id = endereco.getId();
        Endereco enderecoBD = enderecoRepository.findById(id).get();

        if (enderecoRepository.findById(id).isPresent()) {
            enderecoBD.setPadrao(endereco.isPadrao());

            enderecoRepository.saveAndFlush(enderecoBD);
            return Optional.of(enderecoBD);
        }

        return Optional.empty();

    }

    public Optional<Endereco> atualizarStatus(Long id) {
        Endereco usuario = enderecoRepository.findById(id).get();

        if (enderecoRepository.findById(id).isPresent()) {

            if (usuario.isAtivo()) {
                usuario.setAtivo(false);
                enderecoRepository.saveAndFlush(usuario);
                return Optional.of(usuario);
            } else {
                usuario.setAtivo(true);
                enderecoRepository.saveAndFlush(usuario);
                return Optional.of(usuario);
            }

        }

        return Optional.empty();

    }
}
