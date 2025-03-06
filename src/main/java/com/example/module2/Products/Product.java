package com.example.module2.Products;

import jakarta.persistence.*;

import java.util.Set;

import com.example.module2.Orders.CustomerOrder;

import java.util.HashSet;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long product_id;

    @ManyToMany(mappedBy = "purchasedProducts")

    private Set<CustomerOrder> customerWithOrder = new HashSet<>();

    private String name;
    private String description;
    private Double price;

    public Product(Long product_id, String name, String description, Double price) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {

    }

    public String getName() {
        return this.name=name;
    }

    public Set<CustomerOrder> getCustomerWithOrder() {
        return customerWithOrder;
    }

    public void addToOrder(CustomerOrder customerOrder) {
        customerWithOrder.add(customerOrder);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
