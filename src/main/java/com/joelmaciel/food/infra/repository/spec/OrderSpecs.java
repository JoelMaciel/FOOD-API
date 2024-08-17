package com.joelmaciel.food.infra.repository.spec;

import com.joelmaciel.food.domain.model.Order;
import com.joelmaciel.food.domain.repository.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class OrderSpecs {

    private OrderSpecs() {
    }

    public static Specification<Order> usingFilter(OrderFilter orderFilter) {
        return ((root, query, criteriaBuilder) -> {
            root.fetch("restaurant").fetch("kitchen");
            root.fetch("client");

            var predicates = new ArrayList<Predicate>();

            if (orderFilter.getClientId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("client"), orderFilter.getClientId()));
            }

            if (orderFilter.getRestaurantId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("restaurant"), orderFilter.getRestaurantId()));
            }

            if (orderFilter.getDateCreationStart() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"),
                        orderFilter.getDateCreationStart()));
            }

            if (orderFilter.getDateCreationEnd() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("creationDate"),
                        orderFilter.getDateCreationEnd()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
