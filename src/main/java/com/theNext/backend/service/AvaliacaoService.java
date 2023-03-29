package com.theNext.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theNext.backend.model.Avaliacao;
import com.theNext.backend.repository.AvalicaoRepositroy;


@Service
public class AvaliacaoService {
    
    @Autowired
	private AvalicaoRepositroy avalicaoRepositroy;

	public Optional<Avaliacao> cadastrarAvaliacao(Avaliacao avaliacao) {

		return Optional.of(avalicaoRepositroy.save(avaliacao));

	}

}
