package com.service.banking.service;

import com.service.banking.repositories.ContaBancariaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaBancariaService {

    private ContaBancariaRepository contaBancariaRepository;
    public ContaBancariaService(ContaBancariaRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }
}
