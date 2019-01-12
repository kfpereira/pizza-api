package com.fantin.pizza.repositories;

import com.fantin.pizza.domain.Tamanho;
import com.fantin.pizza.domain.type.TypeTamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {

    Tamanho findByTypeTamanho(TypeTamanho tamanho);
}
