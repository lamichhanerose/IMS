package com.example.imsmanagemnt.order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerName(orderDto.getCustomerName());
        order.setProductName(orderDto.getProductName());
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        order.setStatus(orderDto.getStatus());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }
    }
