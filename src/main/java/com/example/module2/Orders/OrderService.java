package com.example.module2.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<CustomerOrder> getProducts() {
        return orderRepository.findAll();
    }

    public void addNewOrder(CustomerOrder order) {
        Optional<CustomerOrder> orderByName = orderRepository
                .findOrderByName(order.getName());

        if (orderByName.isPresent()) {
            throw new IllegalStateException("Order with name " + order.getName() + " already exists");

        }


        orderRepository.save(order);
    }
}
