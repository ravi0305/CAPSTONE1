package com.ecommerce.service;

import com.ecommerce.model.Order;
import com.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }
}
