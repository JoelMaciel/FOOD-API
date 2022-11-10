package com.joel.food.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.joel.food.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>{
	
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	

}
