package com.joelmaciel.food.infrastructure.repository;

import com.joelmaciel.food.domain.model.Permission;
import com.joelmaciel.food.domain.repository.PermissionRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permission> findAll() {
        return entityManager.createQuery("from Permission", Permission.class)
                .getResultList();
    }

    @Override
    public Permission findById(Long id) {
        return entityManager.find(Permission.class, id);
    }

    @Override
    @Transactional
    public Permission save(Permission permission) {
        return entityManager.merge(permission);
    }

    @Override
    @Transactional
    public void remove(Permission permission) {
        Permission permissionTwo = findById(permission.getId());
        entityManager.remove(permissionTwo);
    }
}
