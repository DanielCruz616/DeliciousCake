package com.delicious_cake.delicious_cake_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.entities.InventoryEntity;
import com.delicious_cake.delicious_cake_app.repositories.InventoryRepository;

@Service 
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    //Create Method
    public InventoryEntity create(InventoryEntity inventoryEntity) {
        if (inventoryEntity.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (inventoryEntity.getQuantity() == null || inventoryEntity.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be null or negative");
        }
        return inventoryRepository.save(inventoryEntity);
    }
    
    //Get Method
    public InventoryEntity getById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Inventory not found with id: " + id));
    }
    
    //Get All Method
    public List<InventoryEntity> getAll() {
        return inventoryRepository.findAll();
    }
    
    //Update Method
    public InventoryEntity update(Long id, InventoryEntity inventoryEntity) {
        InventoryEntity existingInventory = getById(id);

        if (inventoryEntity.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (inventoryEntity.getQuantity() == null || inventoryEntity.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be null or negative");
        }

        existingInventory.setProduct(inventoryEntity.getProduct());
        existingInventory.setQuantity(inventoryEntity.getQuantity());
        return inventoryRepository.save(existingInventory);
    }

    //Delete Method
    public void delete(Long id) {
        try {
            InventoryEntity existingInventory = getById(id);
            inventoryRepository.delete(existingInventory);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Inventory not found with id: " + id);
        }
    }
}   
