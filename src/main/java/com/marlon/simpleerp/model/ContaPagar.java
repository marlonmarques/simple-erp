package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_pagar")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    public enum StatusPagamento {
        PENDENTE, PAGO, ATRASADO
    }
}