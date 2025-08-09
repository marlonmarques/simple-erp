package com.marlon.simpleerp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record NotaFiscalDTO(
        Long id,
        String numeroNf,
        String tipo, // "ENTRADA" ou "SAIDA"
        Long clienteId,
        Long fornecedorId,
        BigDecimal valorTotal,
        LocalDate dataEmissao,
        String chaveAcesso,
        String status
) {}