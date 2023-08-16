package com.service.banking.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_agencia")
public class AgenciaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeAgencia;
    private String endereco;

    public AgenciaBancaria() {
    }

    public AgenciaBancaria(Long id, String nomeAgencia, String endereco) {
        this.id = id;
        this.nomeAgencia = nomeAgencia;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeAgencia() {
        return nomeAgencia;
    }

    public void setNomeAgencia(String nomeAgencia) {
        this.nomeAgencia = nomeAgencia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AgenciaBancaria that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
