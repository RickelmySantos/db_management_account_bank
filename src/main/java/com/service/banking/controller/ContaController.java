package com.service.banking.controller;


import com.service.banking.entities.ContaBancaria;
import com.service.banking.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/contas")
    public class ContaController {
        private final ContaBancariaService contaService;

        @Autowired
        public ContaController(ContaBancariaService contaService) {
            this.contaService = contaService;
        }

        @PostMapping
        public ResponseEntity<String> criarConta(@RequestBody ContaBancaria conta) {
            try {
                ContaBancaria novaConta = contaService.criarConta(conta);
                return ResponseEntity.ok("Conta bancária criada com sucesso. ID: " + novaConta.getId());
            } catch (RuntimeException e) {
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


