package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {


}
