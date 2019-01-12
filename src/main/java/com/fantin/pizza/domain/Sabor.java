package com.fantin.pizza.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Sabor {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SABOR_ID")
    @SequenceGenerator(
            name = "SEQ_SABOR_ID",
            allocationSize = 1,
            initialValue = 1,
            sequenceName = "SEQ_SABOR_ID"
    )
    private Long id;

    @Column(name = "DESCRICAO", length = 50)
    private String descricao;

    @Column(name = "VALOR_ADICIONAL")
    private BigDecimal valorAdicional;
}
