package com.delicious_cake.delicious_cake_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.InventoryMovementEntity;
import com.delicious_cake.delicious_cake_app.repositories.InventoryMovementRepository;

@Service 
public class InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;

    public InventoryMovementService(InventoryMovementRepository inventoryMovementRepository) {
        this.inventoryMovementRepository = inventoryMovementRepository;
    }

    //Create Method
    public InventoryMovementEntity create(InventoryMovementEntity inventoryMovementEntity) {
        if (inventoryMovementEntity.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (inventoryMovementEntity.getType() == null) {
            throw new IllegalArgumentException("Movement type cannot be null");
        }
        if (inventoryMovementEntity.getQuantity() == null || inventoryMovementEntity.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be null or negative");
        }
        if (inventoryMovementEntity.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        return inventoryMovementRepository.save(inventoryMovementEntity);
    }

    //Get Method
    public InventoryMovementEntity getById(Long id) {
        return inventoryMovementRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory movement not found with id: " + id));
    }

    //Get All Method
    public List<InventoryMovementEntity> getAll() {
        return inventoryMovementRepository.findAll();
    }

    //Update Method
    public InventoryMovementEntity update(Long id, InventoryMovementEntity inventoryMovementEntity) {
        InventoryMovementEntity existingInventoryMovement = getById(id);

        if (inventoryMovementEntity.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (inventoryMovementEntity.getType() == null) {
            throw new IllegalArgumentException("Movement type cannot be null");
        }
        if (inventoryMovementEntity.getQuantity() == null || inventoryMovementEntity.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be null or negative");
        }
        if (inventoryMovementEntity.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        existingInventoryMovement.setProduct(inventoryMovementEntity.getProduct());
        existingInventoryMovement.setType(inventoryMovementEntity.getType());
        existingInventoryMovement.setQuantity(inventoryMovementEntity.getQuantity());
        existingInventoryMovement.setDate(inventoryMovementEntity.getDate());

        return inventoryMovementRepository.save(existingInventoryMovement);
    }

    //Delete Method
    public void delete(Long id) {
        try {
            InventoryMovementEntity existingInventoryMovement = getById(id);
            inventoryMovementRepository.delete(existingInventoryMovement);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Inventory movement not found with id: " + id);
        }
    }
}
