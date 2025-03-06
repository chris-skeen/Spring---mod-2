package com.example.module2.Customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private static CustomerRepository customerRepository = null;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static List<Customer> getProducts() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerByName = customerRepository
                .findCustomerByName(customer.getName());

        if (customerByName.isPresent()) {
            throw new IllegalStateException("Customer with name " + customer + "already exists");

        }


        customerRepository.save(customer);
    }
}
