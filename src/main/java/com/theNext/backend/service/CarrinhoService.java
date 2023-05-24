package com.theNext.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theNext.backend.model.CarrinhoCompra;
import com.theNext.backend.model.ItensCompra;

import com.theNext.backend.repository.CarrinhoRepository;
import com.theNext.backend.repository.ItensRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ItensRepository itensRepository;

	public Optional<ItensCompra> ItensCarrinho(ItensCompra itensCompra) {
		ItensCompra item = itensRepository.findTopByOrderByCodigoDesc().get();

		if (item != null) {
			Boolean hasFinished = item.getCarrinho();
			if (hasFinished) {
				itensCompra.setCodigo(item.getCodigo() + 1);
			} else {
				itensCompra.setCodigo(item.getCodigo());
			}

		}
		itensRepository.save(itensCompra);
		return Optional.of(itensCompra);

	}

	public Optional<CarrinhoCompra> cadastrarCarrinho(CarrinhoCompra carrinho) {

		if (carrinho.getCliente() != null) {
			Integer codigoMaior = itensRepository.findTopByOrderByCodigoDesc().get().getCodigo();
			carrinho.setCodigoItem(codigoMaior);
			List<ItensCompra> itens = itensRepository.findAll();

			for (ItensCompra item : itens) {
				if (item.getCodigo() == codigoMaior) {
					item.setCarrinho(true);
					itensRepository.save(item);
				}
			}

			return Optional.of(carrinhoRepository.save(carrinho));
		}
		return Optional.empty();

	}

	public Optional<CarrinhoCompra> atualizarStatus(CarrinhoCompra carrinhoCompra) {
		CarrinhoCompra carrinho = carrinhoRepository.findById(carrinhoCompra.getId()).get();

		if (carrinhoRepository.findById(carrinhoCompra.getId()).isPresent()) {

			carrinho.setStatus(carrinhoCompra.getStatus());
			carrinhoRepository.saveAndFlush(carrinho);
			return Optional.of(carrinho);
		}

		return Optional.empty();
	}

}