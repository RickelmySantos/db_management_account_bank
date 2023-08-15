package com.service.banking.repositories;

import com.service.banking.entities.AgenciaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenciaBancariaRepository extends JpaRepository<AgenciaBancaria, Long> {
}
