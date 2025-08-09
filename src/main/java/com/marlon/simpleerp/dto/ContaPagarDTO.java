package com.marlon.simpleerp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaPagarDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        String status,
        Long fornecedorId
) {}