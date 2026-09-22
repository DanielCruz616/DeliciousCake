package com.delicious_cake.delicious_cake_app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.ProductEntity;
import com.delicious_cake.delicious_cake_app.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create Method
    public ProductEntity createProduct(ProductEntity product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be null or negative");
        }
        if (product.getCategory() == null) {
            throw new IllegalArgumentException("Product category cannot be null");
        }
        return productRepository.save(product);
    }

    //Get Method
    public ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    //Get All Method
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    //Update Method
    public ProductEntity updateProduct(Long id, ProductEntity product) {
        ProductEntity existingProduct = getProductById(id);

        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (product.getPrice() == null || product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Product price cannot be null or negative");
        }
        if (product.getCategory() == null) {
            throw new IllegalArgumentException("Product category cannot be null");
        }

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());

        return productRepository.save(existingProduct);
    }

    //Delete Method
    public void deleteProduct(Long id) {
        try {
            ProductEntity existingProduct = getProductById(id);
            productRepository.delete(existingProduct);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
    }
}
