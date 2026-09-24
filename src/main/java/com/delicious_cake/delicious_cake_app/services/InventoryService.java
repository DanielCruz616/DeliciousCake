package com.delicious_cake.delicious_cake_app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.InventoryDTO;
import com.delicious_cake.delicious_cake_app.entities.InventoryEntity;
import com.delicious_cake.delicious_cake_app.entities.ProductEntity;
import com.delicious_cake.delicious_cake_app.mappers.InventoryMapper;
import com.delicious_cake.delicious_cake_app.repositories.InventoryRepository;
import com.delicious_cake.delicious_cake_app.repositories.ProductRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    //Create Method
    public InventoryDTO create(InventoryDTO dto) {

        validateInventory(dto);

        InventoryEntity inventory = InventoryMapper.toEntity(dto);

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with id: " + dto.getProductId()));

        inventory.setProduct(product);

        InventoryEntity savedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(savedInventory);
    }

    //Get Method
    public InventoryDTO getById(Long id) {

        InventoryEntity inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Inventory not found with id: " + id));

        return InventoryMapper.toDTO(inventory);
    }

    //Get All Method
    public List<InventoryDTO> getAll() {

        return inventoryRepository.findAll()
                .stream()
                .map(InventoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method
    public InventoryDTO update(Long id, InventoryDTO dto) {

        InventoryEntity existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Inventory not found with id: " + id));

        validateInventory(dto);

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with id: " + dto.getProductId()));

        existingInventory.setProduct(product);
        existingInventory.setQuantity(dto.getQuantity());

        InventoryEntity updatedInventory =
                inventoryRepository.save(existingInventory);

        return InventoryMapper.toDTO(updatedInventory);
    }

    //Delete Method
    public void delete(Long id) {

        InventoryEntity inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Inventory not found with id: " + id));

        inventoryRepository.delete(inventory);
    }

    //Validation Method
    private void validateInventory(InventoryDTO dto) {

        if (dto.getProductId() == null) {
            throw new IllegalArgumentException(
                    "Product ID cannot be null");
        }

        if (dto.getQuantity() == null || dto.getQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null or negative");
        }
    }
}