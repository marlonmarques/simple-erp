package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.ContaPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

    // Contas vencidas
    @Query("SELECT c FROM ContaPagar c WHERE c.dataVencimento < :hoje AND c.status = 'PENDENTE'")
    List<ContaPagar> findVencidas(@Param("hoje") LocalDate hoje);

    // Contas por status
    List<ContaPagar> findByStatus(ContaPagar.StatusPagamento status);

    // Contas por fornecedor
    List<ContaPagar> findByFornecedorId(Long fornecedorId);

    // Total a pagar no mês
    @Query("SELECT SUM(c.valor) FROM ContaPagar c WHERE FUNCTION('YEAR', c.dataVencimento) = :ano " +
            "AND FUNCTION('MONTH', c.dataVencimento) = :mes AND c.status = 'PENDENTE'")
    Double sumByMesAndAno(@Param("ano") int ano, @Param("mes") int mes);
}