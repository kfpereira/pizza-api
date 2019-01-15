package com.fantin.pizza.domain.model;

import com.fantin.pizza.domain.type.TypePersonalizacao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ADICIONAL",
        uniqueConstraints = @UniqueConstraint(name = "UC_ADIC", columnNames = {"ADICIONAL"}))
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class Adicional {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ADIC_ID")
    @SequenceGenerator(
            name = "SEQ_ADIC_ID",
            allocationSize = 1,
            sequenceName = "SEQ_ADIC_ID"
    )
    private Long id;

    @NotNull(message = "Adicional não pode ser nulo")
    @Column(name = "ADICIONAL")
    @Enumerated(EnumType.STRING)
    private TypePersonalizacao descricao;

    @Column(name = "VALOR_ADICIONAL")
    private BigDecimal valorAdicional;

    @Column(name = "TEMPO_PREPARO")
    private int tempoPreparo;

}
