package com.example.module2.Orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<CustomerOrder> getProduct() {
        List<CustomerOrder> products = orderService.getProducts();
        return products;
    }

    @PostMapping
    public void addOrder(@RequestBody CustomerOrder order) {
        orderService.addNewOrder(order);
    }

}

