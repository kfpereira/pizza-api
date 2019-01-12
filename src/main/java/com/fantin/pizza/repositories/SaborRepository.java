package com.fantin.pizza.repositories;

import com.fantin.pizza.domain.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaborRepository extends JpaRepository<Sabor, Long> {

    Sabor findByDescricao(String descricao);
}
