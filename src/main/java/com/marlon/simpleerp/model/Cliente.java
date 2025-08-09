package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 14)
    private String cpfCnpj;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(name = "cep", length = 8)
    private String cep;

    @Column(name = "logradouro", length = 200)
    private String logradouro;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "cidade", length = 100)
    private String cidade;

    @Column(name = "estado", length = 2)
    private String estado;
}