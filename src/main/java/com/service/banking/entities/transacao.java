package com.service.banking.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_transacao")
public class transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_conta")
    private ContaBancaria contaBancaria;
    private LocalDateTime dataTransacao;
    private String tipoTransacao;
    private BigDecimal valor;

    public transacao() {
    }

    public transacao(Long id, ContaBancaria contaBancaria, LocalDateTime dataTransacao, String tipoTransacao, BigDecimal valor) {
        this.id = id;
        this.contaBancaria = contaBancaria;
        this.dataTransacao = dataTransacao;
        this.tipoTransacao = tipoTransacao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof transacao transacao)) return false;
        return getId().equals(transacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
