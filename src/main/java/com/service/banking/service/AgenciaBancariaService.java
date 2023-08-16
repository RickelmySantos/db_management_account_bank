package com.service.banking.service;

import com.service.banking.entities.AgenciaBancaria;
import com.service.banking.repositories.AgenciaBancariaRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
public class AgenciaBancariaService {

    private final AgenciaBancariaRepository agenciaBancariaRepository;
    public AgenciaBancariaService(AgenciaBancariaRepository agenciaBancariaRepository) {
        this.agenciaBancariaRepository = agenciaBancariaRepository;
    }

    public AgenciaBancaria criarAgencia(AgenciaBancaria agenciaBancaria){
        if(agenciaBancaria.getNomeAgencia() == null){
            throw new IllegalArgumentException("Nome da agência é obrigatório!");
        }
        if (agenciaBancaria.getEnderecoDTO() == null){
            throw new IllegalArgumentException("endereço da agência é obrigatório!");
        }
        return agenciaBancariaRepository.save(agenciaBancaria);
    }

    public List<AgenciaBancaria> listarAgencia(){
        return agenciaBancariaRepository.findAll();
    }
    public AgenciaBancaria atualizarAgencia(Long agenciaId, AgenciaBancaria novaAgencia){
        AgenciaBancaria agenciaExistente = agenciaBancariaRepository.getReferenceById(agenciaId);

        agenciaExistente.setNomeAgencia(novaAgencia.getNomeAgencia());
        agenciaExistente.setEnderecoDTO(novaAgencia.getEnderecoDTO());

        return agenciaBancariaRepository.save(agenciaExistente);
    }

    public void deletarAgencia(Long agenciaId){
        AgenciaBancaria agencia = agenciaBancariaRepository.findById(agenciaId)
                .orElseThrow(() -> new RuntimeException("Agência não localizada"));

                agenciaBancariaRepository.delete(agencia);
    }

}
