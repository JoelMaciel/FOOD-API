package com.joel.food.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joel.food.domain.model.Grupo;

@Repository
public interface GrupoRepository  extends JpaRepository<Grupo, Long>{

}
