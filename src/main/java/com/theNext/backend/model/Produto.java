package com.theNext.backend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Table(name = "tb_produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float avaliacao;

    private String descricao;

    private float preco;

    private int quantidade;

    private boolean isAtivo = true;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("produto")
    private List<Imagem> imagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(float avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public List<Imagem> getImagem() {
        return imagem;
    }

    public void setImagem(List<Imagem> imagem) {
        this.imagem = imagem;
    }

    

}
