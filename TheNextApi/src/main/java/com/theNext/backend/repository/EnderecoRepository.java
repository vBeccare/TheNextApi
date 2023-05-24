package com.theNext.backend.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.Endereco;



@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	public Optional<Endereco> findByEndereco(String endereco);
}