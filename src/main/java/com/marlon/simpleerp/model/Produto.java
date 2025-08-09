package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true, length = 13)
    private String codigoBarras;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(columnDefinition = "TEXT")
    private String descricao;
}