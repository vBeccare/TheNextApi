package com.theNext.backend.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.CarrinhoCompra;


@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoCompra, Long> {

	public Optional<CarrinhoCompra> findById(Long id);
}