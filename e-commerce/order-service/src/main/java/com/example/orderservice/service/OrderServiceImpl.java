package com.example.orderservice.service;


import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.jpa.OrderEntity;
import com.example.orderservice.jpa.OrderRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        orderDetails.setOrderId(UUID.randomUUID().toString());
        orderDetails.setTotalPrice(orderDetails.getUnitPrice() * orderDetails.getQty());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderEntity orderEntity = mapper.map(orderDetails, OrderEntity.class);

        orderRepository.save(orderEntity);

        OrderDto result = mapper.map(orderEntity, OrderDto.class);

        return result;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return new ModelMapper().map(
                orderRepository.findByOrderId(orderId), OrderDto.class);
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
