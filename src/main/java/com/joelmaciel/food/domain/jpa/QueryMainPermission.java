package com.joelmaciel.food.domain.jpa;

import com.joelmaciel.food.FoodApiApplication;
import com.joelmaciel.food.domain.model.Permission;
import com.joelmaciel.food.domain.repository.PermissionRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class QueryMainPermission {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PermissionRepository paymentMethodRepository = applicationContext.getBean(PermissionRepository.class);

        List<Permission> permissions = paymentMethodRepository.findAll();

        for (Permission permission : permissions) {
            System.out.printf("%s - %s\n", permission.getName(), permission.getDescription());
        }
    }

}
