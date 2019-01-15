package com.fantin.pizza.domain.repositories;

import com.fantin.pizza.domain.model.Pedido;
import com.fantin.pizza.domain.model.PedidoAdicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalizacaoRepository extends JpaRepository<PedidoAdicional, Long> {

    List<PedidoAdicional> findByPedido(Pedido pedido);
}
