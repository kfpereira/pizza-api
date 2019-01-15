package com.fantin.pizza.domain.repositories;

import com.fantin.pizza.domain.model.Adicional;
import com.fantin.pizza.domain.type.TypePersonalizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdicionalRepository extends JpaRepository<Adicional, Long> {

    Adicional findByDescricao(TypePersonalizacao personalizacao);

}
