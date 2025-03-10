package com.example.module2.Customers;

import com.example.module2.Orders.CustomerOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    @JsonProperty
    private Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private Set<CustomerOrder> orders = new HashSet<>();

    private String name;
    private Integer age;

    public Customer(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Customer() {

    }

    public String getName() {
        return this.name;
    }

    public Set<CustomerOrder> getOrders() {
        return orders;
    }

    public void addOrderTo(CustomerOrder customerOrder) {
        orders.add(customerOrder);
    }
}