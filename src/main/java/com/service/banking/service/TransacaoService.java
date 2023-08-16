package com.service.banking.service;

import com.service.banking.entities.ContaBancaria;
import com.service.banking.entities.Transacao;
import com.service.banking.enuns.TipoTransacao;
import com.service.banking.repositories.TransacaoRepository;
import com.service.banking.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ContaBancariaService contaService;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository, ContaBancariaService contaService) {
        this.transacaoRepository = transacaoRepository;
        this.contaService = contaService;
    }

    public Transacao realizarDeposito(Long contaId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }

        ContaBancaria conta = contaService.buscarContaPorId(contaId);
        BigDecimal novoSaldo = conta.getSaldo().add(valor);
        conta.setSaldo(novoSaldo);
        contaService.atualizarConta(contaId, conta);

        Transacao transacao = new Transacao(TipoTransacao.DEPOSITO, valor, conta);
        return transacaoRepository.save(transacao);
    }

    public Transacao realizarSaque(Long contaId, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }

        ContaBancaria conta = contaService.buscarContaPorId(contaId);
        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }

        BigDecimal novoSaldo = conta.getSaldo().subtract(valor);
        conta.setSaldo(novoSaldo);
        contaService.atualizarConta(contaId, conta);

        Transacao transacao = new Transacao(TipoTransacao.SAQUE, valor, conta);
        return transacaoRepository.save(transacao);
    }
}
