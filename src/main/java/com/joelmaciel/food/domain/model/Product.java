package com.joelmaciel.food.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Product {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    @ManyToOne
    private Restaurant restaurant;
}
