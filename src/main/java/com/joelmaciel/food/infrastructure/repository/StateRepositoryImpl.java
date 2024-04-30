package com.joelmaciel.food.infrastructure.repository;

import com.joelmaciel.food.domain.model.State;
import com.joelmaciel.food.domain.repository.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<State> findAll() {
        return entityManager.createQuery("from State", State.class)
                .getResultList();
    }

    @Override
    public State findById(Long id) {
        return entityManager.find(State.class, id);
    }

    @Override
    @Transactional
    public State save(State state) {
        return entityManager.merge(state);
    }

    @Override
    @Transactional
    public void remove(State state) {
        State stateTwo = findById(state.getId());
        entityManager.remove(stateTwo);
    }
}
