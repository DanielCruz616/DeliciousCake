package com.delicious_cake.delicious_cake_app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.delicious_cake.delicious_cake_app.dtos.InventoryMovementDTO;
import com.delicious_cake.delicious_cake_app.entities.InventoryMovementEntity;
import com.delicious_cake.delicious_cake_app.entities.ProductEntity;
import com.delicious_cake.delicious_cake_app.mappers.InventoryMovementMapper;
import com.delicious_cake.delicious_cake_app.repositories.InventoryMovementRepository;
import com.delicious_cake.delicious_cake_app.repositories.ProductRepository;

@Service
public class InventoryMovementService {

    private final InventoryMovementRepository inventoryMovementRepository;
    private final ProductRepository productRepository;

    public InventoryMovementService(
            InventoryMovementRepository inventoryMovementRepository,
            ProductRepository productRepository) {

        this.inventoryMovementRepository = inventoryMovementRepository;
        this.productRepository = productRepository;
    }

    //Create Method
    public InventoryMovementDTO create(InventoryMovementDTO dto) {

        validateMovement(dto);

        InventoryMovementEntity movement =
                InventoryMovementMapper.toEntity(dto);

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with id: " + dto.getProductId()));

        movement.setProduct(product);

        InventoryMovementEntity savedMovement =
                inventoryMovementRepository.save(movement);

        return InventoryMovementMapper.toDTO(savedMovement);
    }

    //Get Method
    public InventoryMovementDTO getById(Long id) {

        InventoryMovementEntity movement =
                inventoryMovementRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Inventory movement not found with id: " + id));

        return InventoryMovementMapper.toDTO(movement);
    }

    //Get All Method
    public List<InventoryMovementDTO> getAll() {

        return inventoryMovementRepository.findAll()
                .stream()
                .map(InventoryMovementMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Update Method
    public InventoryMovementDTO update(
            Long id,
            InventoryMovementDTO dto) {

        InventoryMovementEntity existingMovement =
                inventoryMovementRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Inventory movement not found with id: " + id));

        validateMovement(dto);

        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found with id: " + dto.getProductId()));

        existingMovement.setProduct(product);
        existingMovement.setType(dto.getType());
        existingMovement.setQuantity(dto.getQuantity());
        existingMovement.setDate(dto.getDate());

        InventoryMovementEntity updatedMovement =
                inventoryMovementRepository.save(existingMovement);

        return InventoryMovementMapper.toDTO(updatedMovement);
    }

    //Delete Method
    public void delete(Long id) {

        InventoryMovementEntity movement =
                inventoryMovementRepository.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Inventory movement not found with id: " + id));

        inventoryMovementRepository.delete(movement);
    }

    //Validation Method
    private void validateMovement(InventoryMovementDTO dto) {

        if (dto.getProductId() == null) {
            throw new IllegalArgumentException(
                    "Product ID cannot be null");
        }

        if (dto.getType() == null) {
            throw new IllegalArgumentException(
                    "Movement type cannot be null");
        }

        if (dto.getQuantity() == null || dto.getQuantity() < 0) {
            throw new IllegalArgumentException(
                    "Quantity cannot be null or negative");
        }

        if (dto.getDate() == null) {
            throw new IllegalArgumentException(
                    "Date cannot be null");
        }
    }
}