package com.fantin.pizza.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PEDIDO_ID")
    @SequenceGenerator(
            name = "SEQ_PEDIDO_ID",
            allocationSize = 1,
            sequenceName = "SEQ_PEDIDO_ID"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_TAMANHO")
    private Tamanho tamanho;

    @ManyToOne
    @JoinColumn(name = "ID_SABOR")
    private Sabor sabor;

    @Setter
    @Column(name = "TEMPO_TOTAL_PREPARO")
    private Integer tempoTotalPreparo;

    @Setter
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

}
