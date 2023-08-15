package com.service.banking.service;

import com.service.banking.entities.Cliente;
import com.service.banking.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente){
        if(clienteRepository.existsByEmail(cliente.getEmail())){
            throw new RuntimeException("Já existe um cliente com esse email!");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long clienteId, Cliente novoCliente){
    Cliente clienteExistente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado " + clienteId));
    clienteExistente.setNome(novoCliente.getNome());
    clienteExistente.setSobreNome(novoCliente.getSobreNome());
    clienteExistente.setEnderecoDTO(novoCliente.getEnderecoDTO());
    clienteExistente.setEmail(novoCliente.getEmail());
    clienteExistente.setTelefone(novoCliente.getTelefone());

    return clienteRepository.save(clienteExistente);
    }

    public void deletarCliente(Long clienteId){
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não localizado"));

        clienteRepository.delete(cliente);
    }
}
