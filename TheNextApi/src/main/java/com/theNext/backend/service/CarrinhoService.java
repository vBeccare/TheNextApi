package com.theNext.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.theNext.backend.model.CarrinhoCompra;
import com.theNext.backend.model.ItensCompra;
import com.theNext.backend.model.Produto;
import com.theNext.backend.repository.CarrinhoRepository;
import com.theNext.backend.repository.ItensRepository;
import com.theNext.backend.repository.ProdutoRepository;

@Service
public class CarrinhoService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItensRepository itensRepository;

	public Optional<ItensCompra> ItensCarrinho(ItensCompra itensCompra) {


		Optional<Produto> produto = produtoRepository.findById(itensCompra.getId());
		
			itensCompra.setNome(produto.get().getName());
			itensCompra.setValorUnitario(produto.get().getPreco());
			itensCompra.setQuantidade(produto.get().getQuantidade());
			itensRepository.save(itensCompra);
			return Optional.of(itensCompra);

	
	}

	public Optional<CarrinhoCompra> cadastrarCarrinho(CarrinhoCompra carrinho) {

		if (carrinho.getCliente() != null) {
			ItensCompra codigoMaior = itensRepository.findTopByOrderByCodigoDesc().get();
			carrinho.setCodigoItem(codigoMaior.getCodigo());
			codigoMaior.setCodigo(codigoMaior.getCodigo()+1);
			return Optional.of(carrinhoRepository.save(carrinho));
		}
		return Optional.empty();

	}

}
