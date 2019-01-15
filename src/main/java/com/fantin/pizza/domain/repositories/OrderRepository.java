package com.fantin.pizza.domain.repositories;

import com.fantin.pizza.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Pedido, Long> {

}
