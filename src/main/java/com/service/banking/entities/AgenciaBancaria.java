package com.service.banking.entities;

import com.service.banking.dto.EnderecoDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_agencia")
public class AgenciaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeAgencia;
    private EnderecoDTO enderecoDTO;

    public AgenciaBancaria() {
    }

    public AgenciaBancaria(Long id, String nomeAgencia, EnderecoDTO enderecoDTO) {
        this.id = id;
        this.nomeAgencia = nomeAgencia;
        this.enderecoDTO = enderecoDTO;
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

    public EnderecoDTO getEnderecoDTO() {
        return enderecoDTO;
    }

    public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
        this.enderecoDTO = enderecoDTO;
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
