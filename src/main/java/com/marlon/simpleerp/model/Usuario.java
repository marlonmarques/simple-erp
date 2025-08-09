package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, length = 100)
    private String nome;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_perfis", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "perfil")
    @Builder.Default
    private List<String> perfis = new ArrayList<>(); // Ex: "ADMIN", "USER"
}