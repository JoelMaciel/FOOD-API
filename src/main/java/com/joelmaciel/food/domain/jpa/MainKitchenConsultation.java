package com.joelmaciel.food.domain.jpa;

import com.joelmaciel.food.FoodApiApplication;
import com.joelmaciel.food.domain.model.Kitchen;
import com.joelmaciel.food.domain.repository.KitchenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

@Slf4j
public class MainKitchenConsultation {

    public static void main(String[] args) {
        org.springframework.context.ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);
        List<Kitchen> kitchens = kitchenRepository.findAll();
        for (Kitchen kitchen : kitchens) {
            log.info("Kitchen Name : {} " , kitchen.getName());
        }

    }
}
