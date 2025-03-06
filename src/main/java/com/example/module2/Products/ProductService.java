package com.example.module2.Products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static ProductRepository productRepository = null;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        ProductService.productRepository = productRepository;
    }

    public static List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        Optional<Product> productByName = productRepository
                .findProductByName(product.getName());

        if (productByName.isPresent()) {
            throw new IllegalStateException("Product with name " + product.getName() + " already exists");

        }


        productRepository.save(product);
    }

}
