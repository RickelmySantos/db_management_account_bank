package com.service.banking.controller;

import com.service.banking.entities.Cliente;
import com.service.banking.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    @InjectMocks
    private ClienteController clienteController;
    @Mock
    private ClienteService clienteService;
    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        clienteController = new ClienteController(clienteService);
    }
    @Test
    public void temQueCriarUmCliente(){
        //Configura o comportamento do Mock do clienteService
        Cliente cliente = new Cliente();
        Mockito.when(clienteService.criarCliente(Mockito.any())).thenReturn(cliente);

        //Realiza a chamada ao controlador
        ResponseEntity responseEntity = clienteController.criarCliente(new Cliente());

        // Verifica o status HTTP esperad
        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());

        //Verifica a mensagem no corpo da resposta
        assertEquals("Cliente criado com sucesso", responseEntity.getBody());

    }



}

