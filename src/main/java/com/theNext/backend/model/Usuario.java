package com.theNext.backend.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O atributo Nome é Obrigatório!")
    private String name;

    @NotBlank(message = "O atributo Senha é Obrigatório!")
    private String password;

    private String usuario;

    @Schema(example = "email@email.com.br")
    @NotNull(message = "O atributo Email é Obrigatório!")
    @Email(message = "O atributo Email deve ser um email válido!")
    private String email;

    private int cpf;

    private int grupo;

    private boolean isAtivo = true;

    public Usuario(Long id, String name, String password, String usuario, String email, int cpf, int grupo,
            boolean isAtivo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.usuario = usuario;
        this.email = email;
        this.cpf = cpf;
        this.grupo = grupo;
        this.isAtivo = isAtivo;
    }

    public Usuario() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public boolean isAtivo() {
        return isAtivo;
    }

    public void setAtivo(boolean isAtivo) {
        this.isAtivo = isAtivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
