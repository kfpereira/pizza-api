package com.fantin.pizza.domain.model;

import com.fantin.pizza.domain.type.TypeTamanho;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "TAMANHO",
        uniqueConstraints = @UniqueConstraint(name = "uc_size", columnNames = {"SIZE"}))
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Tamanho {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TAM_ID")
    @SequenceGenerator(
            name = "SEQ_TAM_ID",
            allocationSize = 1,
            sequenceName = "SEQ_TAM_ID"
    )
    private Long id;

    @NotNull(message = "Tamanho é Obrigatório")
    @Column(name = "SIZE")
    @Enumerated(EnumType.STRING)
    private TypeTamanho typeTamanho;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "TEMPO_PREPARO")
    private int tempoPreparo;

}
