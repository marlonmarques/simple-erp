package com.marlon.simpleerp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaReceberDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate dataVencimento,
        LocalDate dataRecebimento,
        String status,
        Long clienteId
) {}