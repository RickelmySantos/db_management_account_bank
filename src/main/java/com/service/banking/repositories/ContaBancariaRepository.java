package com.service.banking.repositories;

import com.service.banking.entities.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria,Long> {
}
