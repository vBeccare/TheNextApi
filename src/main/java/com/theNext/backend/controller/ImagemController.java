package com.theNext.backend.controller;

import java.util.Base64;
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

import com.theNext.backend.model.Imagem;
import com.theNext.backend.repository.ImagemRepository;
import com.theNext.backend.service.ImagemService;

@RestController
@RequestMapping("/imagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;

	@Autowired
	private ImagemRepository imagemRepository;

	@GetMapping("/all")
	public ResponseEntity<List<Imagem>> getAll() {

		return ResponseEntity.ok(imagemRepository.findAll());

	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Imagem> postProduto(@Valid @RequestBody Imagem imagem) {
		byte[] imageByte = Base64.getDecoder().decode(imagem.getFileContentBase64());
		imagem.setBytesImage(imageByte);
		return imagemService.cadastrarImagem(imagem)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem(@PathVariable long id) {

		return imagemRepository.findById(id)
				.map(resposta -> {
					imagemRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
