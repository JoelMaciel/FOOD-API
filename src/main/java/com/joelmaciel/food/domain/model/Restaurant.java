package com.joelmaciel.food.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurant {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal freightRate;

    @JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime registrationDate;

    @JsonIgnore
    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();
}
