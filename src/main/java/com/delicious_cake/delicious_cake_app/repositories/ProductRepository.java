package com.delicious_cake.delicious_cake_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delicious_cake.delicious_cake_app.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
