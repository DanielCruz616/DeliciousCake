package com.delicious_cake.delicious_cake_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delicious_cake.delicious_cake_app.entities.InventoryMovementEntity;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovementEntity, Long> {

}
