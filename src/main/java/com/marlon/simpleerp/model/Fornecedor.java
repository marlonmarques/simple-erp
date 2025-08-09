package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fornecedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 14)
    private String cnpj;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 100)
    private String site;
}