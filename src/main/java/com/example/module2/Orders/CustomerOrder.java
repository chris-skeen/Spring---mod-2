package com.example.module2.Orders;
import java.util.HashSet;
import java.util.Set;

import com.example.module2.Products.Product;
import jakarta.persistence.*;

@Entity
@Table
public class CustomerOrder {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long customer_order_id;

    @ManyToMany
    @JoinTable(
        name= "product_purchased",
        joinColumns = @JoinColumn(name="customer_order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> purchasedProducts = new HashSet<>();
    
    private String name;


    public CustomerOrder(Long customer_order_id, String name) {
        this.customer_order_id = customer_order_id;
        this.name = name;
    }

    public CustomerOrder() {

    }

    public String getName() {
        return this.name;
    }

    public Set<Product> getPurchasedProducts() {
        return purchasedProducts;
    }
}