package com.theNext.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.theNext.backend.model.Usuario;
import com.theNext.backend.model.UsuarioLogin;
import com.theNext.backend.repository.ProdutoRepository;
import com.theNext.backend.repository.UsuarioRepository;
import com.theNext.backend.service.ProdutoService;
import com.theNext.backend.service.UsuarioService;




@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/all")
	public ResponseEntity <List<Produto>> getAll(){
		
		return ResponseEntity.ok(produtoRepository.findAll());
		
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Produto>> getAll(@PathVariable String name) {
		return ResponseEntity.ok(produtoRepository.findAllByNameContainingIgnoreCase(name)); 
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {

		return produtoService.cadastrarProduto(produto)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

}
