package com.service.banking.controller;


import com.service.banking.entities.AgenciaBancaria;
import com.service.banking.entities.ContaBancaria;
import com.service.banking.entities.Transacao;
import com.service.banking.service.ContaBancariaService;
import com.service.banking.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
    @RequestMapping("/api/contas")
    public class ContaController {
        private final ContaBancariaService contaService;
        private final TransacaoService transacaoService;

        @Autowired
        public ContaController(ContaBancariaService contaService, TransacaoService transacaoService) {
            this.contaService = contaService;
            this.transacaoService = transacaoService;
        }
        @PostMapping
    public ResponseEntity<?> criarConta(@RequestBody ContaBancaria novaConta) {
        try {
            ContaBancaria bancaria = contaService.criarConta(novaConta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Conta criada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{contaId}/deposito")
    public ResponseEntity<String> realizarDeposito(@PathVariable Long contaId, @RequestParam BigDecimal valor) {
        try {
            Transacao transacao = transacaoService.realizarDeposito(contaId, valor);
            return ResponseEntity.ok("Depósito realizado com sucesso. ID da transação: " + transacao.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{contaId}/saque")
    public ResponseEntity<String> realizarSaque(@PathVariable Long contaId, @RequestParam BigDecimal valor) {
        try {
            Transacao transacao = transacaoService.realizarSaque(contaId, valor);
            return ResponseEntity.ok("Saque realizado com sucesso. ID da transação: " + transacao.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        @GetMapping("/{contaId}")
        public ResponseEntity<ContaBancaria> buscarContaPorId(@PathVariable Long contaId) {
            ContaBancaria conta = contaService.buscarContaPorId(contaId);
            return ResponseEntity.ok(conta);
        }

        @GetMapping
        public ResponseEntity<List<ContaBancaria>> buscarTodasContas() {
            List<ContaBancaria> contas = contaService.buscarTodasContas();
            return ResponseEntity.ok(contas);
        }

        @PutMapping("/{contaId}")
        public ResponseEntity<String> atualizarConta(@PathVariable Long contaId, @RequestBody ContaBancaria novaConta) {
            try {
                ContaBancaria contaAtualizada = contaService.atualizarConta(contaId, novaConta);
                return ResponseEntity.ok("Conta bancária atualizada com sucesso. ID: " + contaAtualizada.getId());
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("/{contaId}")
        public ResponseEntity<String> deletarConta(@PathVariable Long contaId) {
            try {
                contaService.deletarConta(contaId);
                return ResponseEntity.ok("Conta bancária deletada com sucesso. ID: " + contaId);
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
    }


