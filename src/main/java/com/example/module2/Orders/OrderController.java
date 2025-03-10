package com.example.module2.Orders;

import com.example.module2.Customers.Customer;
import com.example.module2.Customers.CustomerRepository;
import com.example.module2.Customers.CustomerService;
import com.example.module2.Products.Product;
import com.example.module2.Orders.CustomerOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository, CustomerService customerService, CustomerRepository customerRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.customerRepository = customerRepository;
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

    @PutMapping("/{customer_order_id}/customer/{customer_id}")
    Customer assignCustomerToOrder(
            @PathVariable Long customer_order_id,
            @PathVariable Long customer_id
    ) {

        Optional<Customer> customerOptional = customerRepository.findById(customer_id);
        Optional<CustomerOrder> customerOrderOptional =  orderRepository.findById(customer_order_id);

        Customer customer = customerOptional.get();
        CustomerOrder customerOrder = customerOrderOptional.get();

        customer.addOrderTo(customerOrder);
        customerOrder.addCustomer(customer);

        orderRepository.save(customerOrder);
        return customerRepository.save(customer);

    }

}

