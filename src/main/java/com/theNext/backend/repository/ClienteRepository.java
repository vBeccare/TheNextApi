package com.theNext.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.Cliente;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByEmail(String email);

	public List<Cliente> findAllByNameContainingIgnoreCase(String name);


}