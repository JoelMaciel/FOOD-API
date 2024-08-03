package com.joelmaciel.food.domain.repository;

import com.joelmaciel.food.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o from Order o join fetch o.client join fetch o.restaurant r join fetch r.kitchen",
            countQuery = "select count(o) from Order o")
    @NonNull
    Page<Order> findAll(@NonNull Pageable pageable);
}
