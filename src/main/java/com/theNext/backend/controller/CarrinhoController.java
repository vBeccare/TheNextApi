package com.theNext.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theNext.backend.model.CarrinhoCompra;
import com.theNext.backend.repository.CarrinhoRepository;
import com.theNext.backend.service.CarrinhoService;

@RestController
@RequestMapping("/carrinhos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarrinhoController {

	@Autowired
	private CarrinhoService carrinhoService;

	@Autowired
	private CarrinhoRepository CarrinhoRepository;

	@GetMapping("/all")
	public ResponseEntity<List<CarrinhoCompra>> getAll() {

		return ResponseEntity.ok(CarrinhoRepository.findAll());

	}

	@PostMapping("/cadastrar")
	public ResponseEntity<CarrinhoCompra> postProduto(@Valid @RequestBody CarrinhoCompra carrinho) {
		return carrinhoService.cadastrarCarrinho(carrinho)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

}
