package com.joelmaciel.food.domain.jpa;

import com.joelmaciel.food.FoodApiApplication;
import com.joelmaciel.food.domain.model.PaymentMethod;
import com.joelmaciel.food.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class QueryPaymentMethodMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(FoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository paymentMethodRepository = applicationContext.getBean(PaymentMethodRepository.class);

        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();

        for (PaymentMethod paymentMethod : paymentMethods) {
            System.out.printf(paymentMethod.getDescription());
        }
    }

}
