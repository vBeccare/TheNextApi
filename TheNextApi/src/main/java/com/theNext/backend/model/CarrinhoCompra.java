package com.theNext.backend.model;


import java.math.BigInteger;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_Carrinho")
public class CarrinhoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigInteger numeroCartao;

    private int codigo;

    private int parcelas;

    private String dataVencimento;

    private int quantidade;

    private String valorTotal;

    private String frete;

    private String totalGeral;

    private String endereçoEntrega;

    private Integer codigoItem;

    private Boolean pagamento;

    private String status = "Aguardando pagamento";

    @ManyToOne
    @JsonIgnoreProperties("Carrinho")
    private Cliente cliente;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("carrinho")
    private List<ItensCompra> item;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFrete() {
        return frete;
    }

    public void setFrete(String frete) {
        this.frete = frete;
    }

    public String getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(String totalGeral) {
        this.totalGeral = totalGeral;
    }

    public String getEndereçoEntrega() {
        return endereçoEntrega;
    }

    public void setEndereçoEntrega(String endereçoEntrega) {
        this.endereçoEntrega = endereçoEntrega;
    }

    public Boolean getPagamento() {
        return pagamento;
    }

    public void setPagamento(Boolean pagamento) {
        this.pagamento = pagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigInteger getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(BigInteger numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(Integer codigoItem) {
        this.codigoItem = codigoItem;
    }

    public List<ItensCompra> getItem() {
        return item;
    }

    public void setItem(List<ItensCompra> item) {
        this.item = item;
    }

    
    
}
