package com.theNext.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theNext.backend.model.CarrinhoCompra;
import com.theNext.backend.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
    
    @Autowired
	private CarrinhoRepository carrinhoRepository;

	public Optional<CarrinhoCompra> cadastrarCarrinho(CarrinhoCompra carrinho) {

		if (carrinho.getCliente() != null && carrinho.getProduto() != null) {
		return Optional.of(carrinhoRepository.save(carrinho));
		}
		return Optional.empty();

	}

}
