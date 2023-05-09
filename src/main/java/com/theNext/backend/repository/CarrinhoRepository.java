package com.theNext.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theNext.backend.model.CarrinhoCompra;


@Repository
public interface CarrinhoRepository extends JpaRepository<CarrinhoCompra, Long> {

}