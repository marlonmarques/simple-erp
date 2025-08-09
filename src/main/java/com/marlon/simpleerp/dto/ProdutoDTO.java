package com.marlon.simpleerp.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        String codigoBarras,
        BigDecimal preco,
        Integer quantidadeEstoque,
        Long fornecedorId,
        String descricao
) {}