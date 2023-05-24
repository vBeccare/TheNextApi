package com.theNext.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theNext.backend.model.Imagem;
import com.theNext.backend.repository.ImagemRepository;

@Service
public class ImagemService {
    
    @Autowired
	private ImagemRepository imagemRepository;

	public Optional<Imagem> cadastrarImagem(Imagem imagem) {

		return Optional.of(imagemRepository.save(imagem));

	}

}
