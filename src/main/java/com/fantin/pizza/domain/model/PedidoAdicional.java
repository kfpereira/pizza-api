package com.fantin.pizza.domain.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "PERSONALIZACAO",
        uniqueConstraints = @UniqueConstraint(name = "UC_PERSON", columnNames = {"ID_PEDIDO", "ID_ADICIONAL"}))
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Builder
public class PedidoAdicional {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_PERSON_ID")
    @SequenceGenerator(
            name = "SEQ_PERSON_ID",
            allocationSize = 1,
            sequenceName = "SEQ_PERSON_ID"
    )
    private Long id;

    @NotNull(message = "Pedido é Obrigatório")
    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO")
    private Pedido pedido;

    @NotNull(message = "Adicional não pode ser nulo no pedido")
    @ManyToOne
    @JoinColumn(name = "ID_ADICIONAL")
    private Adicional adicional;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "TEMPO_PREPARO")
    private int tempoPreparo;

}
