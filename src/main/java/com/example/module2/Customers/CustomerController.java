package com.example.module2.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<Customer> getProduct() {
        return CustomerService.getProducts();
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addNewCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer viewOneCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return customer;
    }

    @DeleteMapping("/{id}")
    public void deleteOneCustomer(@PathVariable Long id) {
        Customer customer = customerService.getDeleteById(id);
    }
}
