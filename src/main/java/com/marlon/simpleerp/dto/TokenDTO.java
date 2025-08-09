package com.marlon.simpleerp.dto;

public record TokenDTO(
        String token,
        String tipo,
        Long id,
        String nome,
        String email,
        String perfil
) {
    public TokenDTO(String token, String nome, String email, String perfil, Long id) {
        this(token, "Bearer", id, nome, email, perfil);
    }
}