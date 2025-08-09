package com.marlon.simpleerp.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "notas_fiscais")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotaFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_nf", nullable = false)
    private String numeroNf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoNota tipo; // ENTRADA ou SAIDA

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "data_emissao")
    private LocalDate dataEmissao;

    @Column(name = "chave_acesso", length = 44)
    private String chaveAcesso;

    @Enumerated(EnumType.STRING)
    private Status status; // EMITIDA, CANCELADA, AUTORIZADA

    public enum TipoNota {
        ENTRADA, SAIDA
    }

    public enum Status {
        EMITIDA, AUTORIZADA, CANCELADA
    }
}