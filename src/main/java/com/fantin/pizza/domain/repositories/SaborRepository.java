package com.fantin.pizza.domain.repositories;

import com.fantin.pizza.domain.model.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {

    Sabor findByDescricao(String descricao);
}
