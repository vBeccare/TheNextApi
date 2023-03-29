package com.theNext.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.theNext.backend.model.Produto;
import com.theNext.backend.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
	private ProdutoRepository produtoRepository;

	public Optional<Produto> cadastrarProduto(Produto produto) {

		return Optional.of(produtoRepository.save(produto));

	}

	public Page<Produto> listAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

}
