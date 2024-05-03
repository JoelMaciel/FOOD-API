package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("from Restaurant r join fetch r.kitchen")
    List<Restaurant> findAll();


}
