package com.service.banking.service;

import com.service.banking.entities.AgenciaBancaria;
import com.service.banking.entities.ContaBancaria;
import com.service.banking.repositories.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaBancariaService {
    private final ContaBancariaRepository contaRepository;
    private final AgenciaBancariaService agenciaService;

    @Autowired
    public ContaBancariaService(ContaBancariaRepository contaRepository, AgenciaBancariaService agenciaService) {
        this.contaRepository = contaRepository;
        this.agenciaService = agenciaService;
    }

    public ContaBancaria criarConta(ContaBancaria conta) {
        AgenciaBancaria agencia = agenciaService.buscarAgenciaPorId(conta.getAgenciaBancaria().getId());
        conta.setAgenciaBancaria(agencia);

        return contaRepository.save(conta);
    }

    public ContaBancaria buscarContaPorId(Long contaId) {
        return contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta bancária não encontrada com o ID: " + contaId));
    }

    public List<ContaBancaria> buscarTodasContas() {
        return contaRepository.findAll();
    }

    public ContaBancaria atualizarConta(Long contaId, ContaBancaria novaConta) {
        ContaBancaria contaExistente = buscarContaPorId(contaId);

        AgenciaBancaria agencia = agenciaService.buscarAgenciaPorId(novaConta.getAgenciaBancaria().getId());
        contaExistente.setAgenciaBancaria(agencia);

        contaExistente.setTipoConta(novaConta.getTipoConta());
        contaExistente.setSaldo(novaConta.getSaldo());

        return contaRepository.save(contaExistente);
    }

    public void deletarConta(Long contaId) {
        ContaBancaria conta = buscarContaPorId(contaId);
        contaRepository.delete(conta);
    }
}
