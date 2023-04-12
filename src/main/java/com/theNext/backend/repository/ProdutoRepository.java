package com.theNext.backend.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.Produto;




@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

 
	public Optional<Produto> findById(Long id);
    public Page<Produto> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}