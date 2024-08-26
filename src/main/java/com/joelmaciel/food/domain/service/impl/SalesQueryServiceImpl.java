package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.response.DailySalesDTO;
import com.joelmaciel.food.domain.enums.OrderStatus;
import com.joelmaciel.food.domain.filter.DailySalesFilter;
import com.joelmaciel.food.domain.model.Order;
import com.joelmaciel.food.domain.service.SalesQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SalesQueryServiceImpl implements SalesQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<DailySalesDTO> consultDailySales(DailySalesFilter dailySalesFilter, String timeOffset) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(DailySalesDTO.class);
        var root = query.from(Order.class);
        var predicates = new ArrayList<Predicate>();

        var functionConvertTzCreationDate = builder.function(
                "convert_tz", Date.class,root.get("creationDate"),
                builder.literal("+00:00"), builder.literal(timeOffset)
        );

        var functionCreationDate = builder.function(
                "date", Date.class, functionConvertTzCreationDate
        );

        var selection = builder.construct(DailySalesDTO.class,
                functionCreationDate,
                builder.count(root.get("id")),
                builder.sum(root.get("totalValue")));

        if (dailySalesFilter.getRestaurantId() != null) {
            predicates.add(builder.equal(root.get("restaurant"), dailySalesFilter.getRestaurantId()));
        }

        if (dailySalesFilter.getDateCreationStart() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("creationDate"),
                    dailySalesFilter.getDateCreationStart()));
        }
        if (dailySalesFilter.getDateCreationEnd() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("creationDate"),
                    dailySalesFilter.getDateCreationEnd()));
        }

        predicates.add(root.get("status").in(
                OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(functionCreationDate);

        return entityManager.createQuery(query).getResultList();
    }
}
