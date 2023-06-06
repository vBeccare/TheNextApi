/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.theNext.backend.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nonapi.io.github.classgraph.utils.Assert;

import static org.junit.Assert.*;

/**
 *
 * @author vbeccare
 */
public class ProdutoIT {

    public ProdutoIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    private Long idProduto1 = new Long(1);
    private Long idProduto2 = new Long(2);
    List<Produto> produtos = new ArrayList<>();

    @Before
    public void setUp() {
        Produto produto01 = new Produto(idProduto1, "S22", "Celular com 128GB", 4, 8000, 10, true);
        Produto produto02 = new Produto(idProduto2, "S23", "Celular com 256GB", 4, 12000, 10, true);

        produtos.add(produto01);
        produtos.add(produto02);

    }

    @Test
    public void testCadastrarProduto() {

        int listaTamanho = produtos.size();

        assertEquals(listaTamanho, 2);
    }

    @Test
    public void testPesquisaProduto() {

        Long produtoId = produtos.get(0).getId();

        assertEquals(idProduto1, produtoId);
    }

}
