package com.example.module2.Orders;
import java.util.HashSet;
import java.util.Set;

import com.example.module2.Customers.Customer;
import com.example.module2.Products.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name= "product_purchased",
        joinColumns = @JoinColumn(name="customer_order_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> purchasedProducts = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName = "id")
    private Customer customer;
    
    private String name;


    public CustomerOrder(Long customer_order_id, String name) {
        this.id = customer_order_id;
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

    public void addPurchasedProduct(Product product) {
        this.purchasedProducts.add(product);
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "customer_order_id=" + id +
                ", purchasedProducts=" + purchasedProducts +
                ", name='" + name + '\'' +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addCustomer(Customer customer) {
        this.customer = customer;
    }
}