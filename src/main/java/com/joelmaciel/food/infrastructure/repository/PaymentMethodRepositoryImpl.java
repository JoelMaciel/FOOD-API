package com.joelmaciel.food.infrastructure.repository;

import com.joelmaciel.food.domain.model.PaymentMethod;
import com.joelmaciel.food.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PaymentMethod> findAll() {
        return entityManager.createQuery("from PaymentMethod", PaymentMethod.class)
                .getResultList();
    }

    @Override
    public PaymentMethod findById(Long id) {
        return entityManager.find(PaymentMethod.class, id);
    }

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return entityManager.merge(paymentMethod);
    }

    @Override
    @Transactional
    public void remove(PaymentMethod paymentMethod) {
        PaymentMethod paymentMethodTwo = findById(paymentMethod.getId());
        entityManager.remove(paymentMethodTwo);
    }
}
