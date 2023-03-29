package com.theNext.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.Avaliacao;





@Repository
public interface AvalicaoRepositroy extends JpaRepository<Avaliacao, Long> {

	public Optional<Avaliacao> findById(Long id);

}
