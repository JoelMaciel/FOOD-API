package com.joelmaciel.food.domain.jpa;

import com.joelmaciel.food.FoodApiApplication;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class MainKitchenUpdate {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setName("Brazilian");

        kitchenRepository.save(kitchen);
    }

}

