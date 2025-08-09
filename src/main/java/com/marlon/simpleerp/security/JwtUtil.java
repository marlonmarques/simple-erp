package com.marlon.simpleerp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Extrai o username (email) do token
    public String extrairUsername(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    // Extrai a data de expiração
    public Date extrairDataExpiracao(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }

    public <T> T extrairClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extrairTodosClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extrairTodosClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    // Verifica se o token expirou
    private Boolean isTokenExpirado(String token) {
        return extrairDataExpiracao(token).before(new Date());
    }

    // Gera token a partir do usuário
    public String gerarToken(String email, String nome, String perfil) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("nome", nome);
        claims.put("perfil", perfil);
        return criarToken(claims, email);
    }

    private String criarToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    // Valida o token
    public Boolean validarToken(String token) {
        try {
            final String username = extrairUsername(token);
            return (username != null && !isTokenExpirado(token));
        } catch (Exception e) {
            return false;
        }
    }
}