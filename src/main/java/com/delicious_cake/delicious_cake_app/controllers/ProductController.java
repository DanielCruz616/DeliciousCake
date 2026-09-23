package com.delicious_cake.delicious_cake_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delicious_cake.delicious_cake_app.entities.ProductEntity;
import com.delicious_cake.delicious_cake_app.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create
    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(
            @RequestBody ProductEntity product) {

        ProductEntity createdProduct = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(
            @PathVariable Long id) {

        ProductEntity product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {

        List<ProductEntity> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductEntity product) {

        ProductEntity updatedProduct =
                productService.updateProduct(id, product);

        return ResponseEntity.ok(updatedProduct);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}

