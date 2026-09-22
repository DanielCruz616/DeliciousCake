package com.delicious_cake.delicious_cake_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delicious_cake.delicious_cake_app.entities.SaleEntity;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

}
