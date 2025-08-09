package com.marlon.simpleerp.dto;

public record ClienteDTO(
        Long id,
        String nome,
        String cpfCnpj,
        String email,
        String telefone,
        String cep,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String estado
) {}