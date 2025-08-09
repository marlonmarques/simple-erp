package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contas_receber")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_recebimento")
    private LocalDate dataRecebimento;

    @Enumerated(EnumType.STRING)
    private StatusRecebimento status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public enum StatusRecebimento {
        PENDENTE, RECEBIDO, ATRASADO
    }
}