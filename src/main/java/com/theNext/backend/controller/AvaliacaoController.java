package com.theNext.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theNext.backend.model.Avaliacao;
import com.theNext.backend.repository.AvalicaoRepositroy;
import com.theNext.backend.service.AvaliacaoService;

@RestController
@RequestMapping("/avaliacao")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvaliacaoController {

    @Autowired
	private AvaliacaoService avaliacaoService;

	@Autowired
	private AvalicaoRepositroy avalicaoRepositroy;
    
    @GetMapping("/all")
	public ResponseEntity <List<Avaliacao>> getAll(){
		
		return ResponseEntity.ok(avalicaoRepositroy.findAll());
		
	}

    @PostMapping("/cadastrar")
	public ResponseEntity<Avaliacao> postProduto(@Valid @RequestBody Avaliacao avaliacao) {

		return avaliacaoService.cadastrarAvaliacao(avaliacao)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

    @DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {
		
		return avalicaoRepositroy.findById(id)
				.map(resposta -> {
					avalicaoRepositroy.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
