package com.theNext.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.ItensCompra;

@Repository
public interface ItensRepository extends JpaRepository<ItensCompra, Long> {

	public Optional<ItensCompra> findById(Long id);

	public Optional<ItensCompra> findTopByOrderByCodigoDesc();
}

