package com.service.banking.controller;

import com.service.banking.entities.Cliente;
import com.service.banking.service.ClienteService;
import org.junit.jupiter.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Test
    public void deveLancarExcecaoParaNomeEmBranco(){
        //Criando um cliente com nome em branco
        Cliente cliente = new Cliente();
        cliente.setNome("");

        // Configura o comportamento do clienteService para lançar uma exceção
        Mockito.when(clienteService.criarCliente(cliente))
                .thenThrow(new RuntimeException("campo nome não pode está em branco!"));

        // Tenta criar o cliente, o método deve lançar a exceção configurad
       Assertions.assertThrows(RuntimeException.class, () -> clienteController.criarCliente(cliente));

        // Verifica que o método do clienteService foi chamado uma vez
        Mockito.verify(clienteService,Mockito.times(1)).criarCliente(cliente);
    }
    @Test
    public void deveAtualizarUmCliente(){
        Long clienteId = 2L;
        Cliente clienteAtualizado = new Cliente();

        Mockito.when(clienteService.atualizarCliente(Mockito.eq(clienteId),Mockito.any())).thenReturn(clienteAtualizado);

        ResponseEntity responseEntity = clienteController.atualizarCliente(clienteId,new Cliente());

        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals("Cliente atualizado com sucesso!",responseEntity.getBody());
    }

    @Test
    public void deveLancarExcecaoAoAtualizarClienteInexistente() {
        Long clienteId = 1L;
        Mockito.when(clienteService.atualizarCliente(Mockito.eq(clienteId), Mockito.any()))
                .thenThrow(new RuntimeException("Cliente não encontrado"));

        assertThrows(RuntimeException.class, () -> clienteController.atualizarCliente(clienteId, new Cliente()));
    }

    @Test
    public void deveDeletarUmCliente(){
        Long clienteId= 1L;
        Mockito.doNothing().when(clienteService).deletarCliente(Mockito.eq(clienteId));

        ResponseEntity<?> responseEntity = clienteController.deletarCliente(clienteId);

        assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        assertEquals("Cliente deletado!",responseEntity.getBody());
    }

}

