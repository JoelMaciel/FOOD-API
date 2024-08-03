package com.joelmaciel.food.domain.model;

import com.joelmaciel.food.domain.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "`order`")
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

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    private OffsetDateTime creationDate;
    private OffsetDateTime confirmationDate;
    private OffsetDateTime cancellationDate;
    private OffsetDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
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

    public void calculateTotalValue() {
        this.subTotal = getItems().stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setFreightRate() {
        setFreightRate(getRestaurant().getFreightRate());
    }

    public void assignOrderToItems() {
        getItems().forEach(item -> item.setOrder(this));
    }

}
