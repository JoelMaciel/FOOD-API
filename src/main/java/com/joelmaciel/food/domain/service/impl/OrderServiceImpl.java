package com.joelmaciel.food.domain.service.impl;

import com.joelmaciel.food.api.dto.converter.AddressConverter;
import com.joelmaciel.food.api.dto.converter.OrderConverter;
import com.joelmaciel.food.api.dto.request.OrderItemRequestDTO;
import com.joelmaciel.food.api.dto.request.OrderRequestDTO;
import com.joelmaciel.food.api.dto.response.OrderDTO;
import com.joelmaciel.food.api.dto.response.OrderSummaryDTO;
import com.joelmaciel.food.domain.enums.OrderStatus;
import com.joelmaciel.food.domain.exception.BusinessException;
import com.joelmaciel.food.domain.exception.OrderNotFoundException;
import com.joelmaciel.food.domain.model.*;
import com.joelmaciel.food.domain.repository.OrderRepository;
import com.joelmaciel.food.domain.repository.filter.OrderFilter;
import com.joelmaciel.food.domain.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    public static final String NOT_ACCEPTED_BY_THIS_RESTAURANT = "Payment method '%s' is not accepted by this restaurant.";
    private final OrderRepository orderRepository;
    private final CityService cityService;
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final PaymentMethodService paymentMethodService;
    private final ProductService productService;

    @Override
    public Page<OrderSummaryDTO> findAll(Specification<Order> orderSpecification, Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(orderSpecification, pageable);
        return OrderConverter.orderDTOPage(orders);
    }

    @Override
    public OrderDTO findById(String code) {
        return OrderConverter.toDTO(optionalOrder(code));
    }

    @Override
    public Order optionalOrder(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new OrderNotFoundException(code));
    }

    @Transactional
    @Override
    public OrderDTO addOrder(OrderRequestDTO orderRequestDTO) {
        Order order = toDomainObject(orderRequestDTO);
        validateOrder(orderRequestDTO, order);
        order.calculateTotalValue();
        orderRepository.save(order);
        return OrderConverter.toDTO(order);
    }


    private Order toDomainObject(OrderRequestDTO orderRequestDTO) {
        Restaurant restaurant = restaurantService.optinalRestaurant(orderRequestDTO.getRestaurant().getId());
        City city = cityService.optionalCity(orderRequestDTO.getAddressDelivery().getCity().getId());
        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setAddressDelivery(AddressConverter.toEntity(orderRequestDTO.getAddressDelivery(), city));
        order.setStatus(OrderStatus.CREATED);
        order.setPaymentMethod(new PaymentMethod(orderRequestDTO.getPaymentMethod().getId()));
        order.setItems(convertItems(orderRequestDTO.getItems(), order));
        order.setClient(userService.optionalUser(1L));

        return order;
    }

    private List<OrderItem> convertItems(List<OrderItemRequestDTO> itemsDTO, Order order) {
        return itemsDTO.stream().map(itemDTO -> {
            Product product = productService.optionalProductRestaurant(order.getRestaurant().getId(), itemDTO.getProductId());
            return OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemDTO.getQuantity())
                    .unitPrice(product.getPrice())
                    .observation(itemDTO.getObservation())
                    .build();
        }).toList();
    }

    private void validateOrder(OrderRequestDTO orderRequestDTO, Order order) {
        City city = cityService.optionalCity(orderRequestDTO.getAddressDelivery().getCity().getId());
        Restaurant restaurant = order.getRestaurant();
        PaymentMethod paymentMethod = paymentMethodService.optionalPaymentMethod(orderRequestDTO.getPaymentMethod().getId());

        order.getAddressDelivery().setCity(city);
        order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);

        if (restaurant.doesNotAcceptPaymentForm(paymentMethod)) {
            throw new BusinessException(String.format(NOT_ACCEPTED_BY_THIS_RESTAURANT, paymentMethod.getDescription()));
        }
    }
}
