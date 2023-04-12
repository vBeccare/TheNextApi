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

	public Page<Produto> listAll(Pageable pageable) {
		return produtoRepository.findAll(pageable);
	}

	public Page<Produto> findAllByNameContainingIgnoreCase(String name, Pageable pageable) {
		return produtoRepository.findAllByNameContainingIgnoreCase(name, pageable);
	}

	public Produto getProductByid(Long id) {
		return produtoRepository.findById(id).get();
	}

	public Optional<Produto> atualizarProduto(Produto produto) {
		Long id = produto.getId();
		Produto produtoBD = produtoRepository.findById(id).get();

		if (produtoRepository.findById(id).isPresent()) {
			if (produto.getName() != null) {
				produtoBD.setName(produto.getName());
			}

			if (produto.getDescricao() != null) {
				produtoBD.setDescricao(produto.getDescricao());
			}

			if (produto.getPreco() != 0) {
				produtoBD.setPreco(produto.getPreco());
			}

			if (produto.getQuantidade() != 0) {
				produtoBD.setQuantidade(produto.getQuantidade());
			}

			if (produto.getAvaliacao() != 0) {
				produtoBD.setAvaliacao(produto.getAvaliacao());
			}

			if (produto.getImgPrincipal() != 0) {
				produtoBD.setImgPrincipal(produto.getImgPrincipal());
			}

			produtoRepository.saveAndFlush(produtoBD);
			return Optional.of(produtoBD);
		}

		return Optional.empty();

	}

	public Optional<Produto> atualizarStatus(Long id) {
		Produto produto = produtoRepository.findById(id).get();

		if (produtoRepository.findById(id).isPresent()) {

			if (produto.isAtivo()) {
				produto.setAtivo(false);
				produtoRepository.saveAndFlush(produto);
				return Optional.of(produto);
			} else {
				produto.setAtivo(true);
				produtoRepository.saveAndFlush(produto);
				return Optional.of(produto);
			}

		}

		return Optional.empty();

	}
}
