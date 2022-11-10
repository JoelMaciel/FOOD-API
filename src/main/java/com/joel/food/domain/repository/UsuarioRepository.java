package com.joel.food.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.joel.food.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}
