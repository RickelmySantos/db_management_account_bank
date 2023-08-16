package com.service.banking.controller;

import com.service.banking.entities.Cliente;
import com.service.banking.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente cliente1 = clienteService.criarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente criado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> atualizarCleinte(@PathVariable Long clienteId, @RequestBody Cliente novoCliente) {
        try {
            Cliente clienteAtualizado = clienteService.atualizarCliente(clienteId, novoCliente);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long clienteId) {
        try {

            clienteService.deletarCliente(clienteId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente deletado!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}