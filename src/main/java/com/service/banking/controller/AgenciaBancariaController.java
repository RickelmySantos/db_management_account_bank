package com.service.banking.controller;

import com.service.banking.entities.AgenciaBancaria;
import com.service.banking.service.AgenciaBancariaService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencias")
public class AgenciaBancariaController {

    private final AgenciaBancariaService agenciaBancariaService;

    public AgenciaBancariaController(AgenciaBancariaService agenciaBancariaService) {
        this.agenciaBancariaService = agenciaBancariaService;
    }

    @PostMapping
    public ResponseEntity<?> criarAgencia(@RequestBody AgenciaBancaria agencia){
        try {
            AgenciaBancaria bancaria = agenciaBancariaService.criarAgencia(agencia);
            return ResponseEntity.status(HttpStatus.CREATED).body("Agência criada com sucesso!");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<List<AgenciaBancaria>> listarAgencias(){
        List<AgenciaBancaria> bancarias = agenciaBancariaService.listarAgencia();
        return ResponseEntity.ok(bancarias);
    }
}
