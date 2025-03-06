package com.example.module2.Products;

import com.example.module2.Orders.CustomerOrder;
import com.example.module2.Orders.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void addProduct(@RequestBody Product product) {

        productService.addNewProduct(product);
    }

    @PutMapping("/{customer_order_id}/products/{product_id}")
    Product purchasedProduct(
            @PathVariable Long customer_order_id,
            @PathVariable Long product_id
    ) {

        Product product = productRepository.getOne(product_id);
        CustomerOrder customerOrder =  orderRepository.getOne(customer_order_id);
        product.addToOrder(customerOrder);
        return productRepository.save(product);

    }

}
