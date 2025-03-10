package com.example.module2.Products;

import com.example.module2.Customers.Customer;
import com.example.module2.Orders.CustomerOrder;
import com.example.module2.Orders.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public List<Product> getProduct() {
        return ProductService.getProducts();
    }

    @GetMapping("/{id}")
    public Product viewOneProduct(@PathVariable Long id) {
        Product product = productService.getCustomerById(id);
        return product;
    }

    @PostMapping
    public void addProduct(@RequestBody Product product) {

        productService.addNewProduct(product);
    }

    @PutMapping("/{customer_order_id}/products/{product_id}")
    Product purchasedProduct(
            @PathVariable Long customer_order_id,
            @PathVariable Long product_id
    ) {

        Optional<Product> productOptional = productRepository.findById(product_id);
        Optional<CustomerOrder> customerOrderOptional =  orderRepository.findById(customer_order_id);

        if  (productOptional.isEmpty()) {
            throw new IllegalStateException("Product not found");
        }
        Product product = productOptional.get();
        if  (customerOrderOptional.isEmpty()) {
            throw new IllegalStateException("Customer order not found");
        }
        CustomerOrder customerOrder = customerOrderOptional.get();

        product.addToOrder(customerOrder);
        customerOrder.addPurchasedProduct(product);

        orderRepository.save(customerOrder);
        return productRepository.save(product);

    }

    @DeleteMapping("/{id}")
    public void deleteOneProduct(@PathVariable Long id) {
        Product product = productService.getDeleteById(id);

    }

}
