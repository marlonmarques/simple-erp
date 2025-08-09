package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.ContaReceber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {

    // Contas atrasadas
    @Query("SELECT c FROM ContaReceber c WHERE c.dataVencimento < :hoje AND c.status = 'PENDENTE'")
    List<ContaReceber> findAtrasadas(@Param("hoje") LocalDate hoje);

    // Contas por status
    List<ContaReceber> findByStatus(ContaReceber.StatusRecebimento status);

    // Contas por cliente
    List<ContaReceber> findByClienteId(Long clienteId);

    // Total a receber no mês
    @Query("SELECT SUM(c.valor) FROM ContaReceber c WHERE FUNCTION('YEAR', c.dataVencimento) = :ano " +
            "AND FUNCTION('MONTH', c.dataVencimento) = :mes AND c.status = 'PENDENTE'")
    Double sumByMesAndAno(@Param("ano") int ano, @Param("mes") int mes);
}