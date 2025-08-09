package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    // Busca por CNPJ
    Optional<Fornecedor> findByCnpj(String cnpj);

    // Busca por email
    Optional<Fornecedor> findByEmail(String email);

    // Busca por nome
    java.util.List<Fornecedor> findByNomeContainingIgnoreCase(String nome);
}