package com.theNext.backend.controller;



import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theNext.backend.model.Produto;
import com.theNext.backend.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/all")
	public ResponseEntity<Page<Produto>> listAll(Pageable pageable) {
		return new ResponseEntity<>(produtoService.listAll(pageable), HttpStatus.OK);

	}

	@GetMapping("/produto/{id}")
	public Produto getProductById(@PathVariable Long id) {
		return produtoService.getProductByid(id);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Page<Produto>> getByName(@PathVariable String name, Pageable pageable) {
		return new ResponseEntity<>(produtoService.findAllByNameContainingIgnoreCase(name, pageable), HttpStatus.OK);
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
		return produtoService.cadastrarProduto(produto)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	
	@PutMapping("/atualizar")
	public ResponseEntity<Produto> putUsuario(@Valid @RequestBody Produto produto) {
		return produtoService.atualizarProduto(produto)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PutMapping("/atualizar-status")
	public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto) {
		Long id = produto.getId();
		return produtoService.atualizarStatus(id)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

}
