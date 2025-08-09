package com.marlon.simpleerp.repository;

import com.marlon.simpleerp.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    // Busca por tipo (ENTRADA ou SAIDA)
    List<NotaFiscal> findByTipo(NotaFiscal.TipoNota tipo);

    // Busca por cliente
    List<NotaFiscal> findByClienteId(Long clienteId);

    // Busca por fornecedor
    List<NotaFiscal> findByFornecedorId(Long fornecedorId);

    // Busca por período
    @Query("SELECT n FROM NotaFiscal n WHERE n.dataEmissao BETWEEN :inicio AND :fim")
    List<NotaFiscal> findByPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    // Contagem por status
    long countByStatus(NotaFiscal.Status status);
}