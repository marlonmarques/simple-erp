package com.marlon.simpleerp.dto;

public record FornecedorDTO(
        Long id,
        String nome,
        String cnpj,
        String email,
        String telefone,
        String site
) {}