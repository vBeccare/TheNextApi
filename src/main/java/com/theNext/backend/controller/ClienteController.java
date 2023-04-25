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

import com.theNext.backend.model.Cliente;
import com.theNext.backend.model.ClienteLogin;
import com.theNext.backend.repository.ClienteRepository;
import com.theNext.backend.service.ClienteService;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAll() {

        return ResponseEntity.ok(clienteRepository.findAll());

    }

    @PostMapping("/logar")
    public ResponseEntity<ClienteLogin> login(@RequestBody Optional<ClienteLogin> user) {
        return clienteService.autenticarCliente(user)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> postCliente(@Valid @RequestBody Cliente cliente) {

        return clienteService.cadastrarCliente(cliente)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

    }

    @PutMapping("/atualizar")
    public ResponseEntity<Cliente> putCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(cliente)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
