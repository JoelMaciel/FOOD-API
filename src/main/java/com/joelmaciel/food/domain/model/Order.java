package com.joelmaciel.food.domain.model;

import com.joelmaciel.food.domain.enums.OrderStatus;
import com.joelmaciel.food.domain.model.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Order {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal subTotal;
    private BigDecimal freightRate;
    private BigDecimal totalValue;

    @Embedded
    private Address addressDelivery;
    private OrderStatus status;

    @CreationTimestamp
    private LocalDateTime creationDate;
    private LocalDateTime confirmationDate;
    private LocalDateTime cancellationDate;
    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

}
