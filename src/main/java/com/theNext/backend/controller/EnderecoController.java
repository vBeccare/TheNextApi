package com.theNext.backend.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theNext.backend.model.Endereco;
import com.theNext.backend.repository.EnderecoRepository;
import com.theNext.backend.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Endereco>> getAll() {

        return ResponseEntity.ok(enderecoRepository.findAll());

    }   


    @PostMapping("/cadastrar")
    public ResponseEntity<Endereco> postEndereco(@Valid @RequestBody Endereco endereco) {

        return enderecoService.cadastrarEndereco(endereco)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @PutMapping("/atualizar")
    public ResponseEntity<Endereco> putEndereco(@Valid @RequestBody Endereco endereco) {
        return enderecoService.atualizarEndereco(endereco)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/atualizar-status")
    public ResponseEntity<Endereco> putUsuarioStatus(@Valid @RequestBody Endereco endereco) {
        String enderecoAlt = endereco.getEndereco();
        return enderecoService.atualizarStatus(enderecoAlt)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
