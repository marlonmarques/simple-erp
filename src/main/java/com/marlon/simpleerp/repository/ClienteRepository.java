package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    // Busca por CPF/CNPJ
    Optional<Cliente> findByCpfCnpj(String cpfCnpj);

    // Busca por email
    Optional<Cliente> findByEmail(String email);

    // Busca por nome
    java.util.List<Cliente> findByNomeContainingIgnoreCase(String nome);
}