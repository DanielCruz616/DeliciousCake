package com.delicious_cake.delicious_cake_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delicious_cake.delicious_cake_app.dtos.InventoryMovementDTO;
import com.delicious_cake.delicious_cake_app.services.InventoryMovementService;

@RestController
@RequestMapping("/inventory-movements")
public class InventoryMovementController {

    private final InventoryMovementService inventoryMovementService;

    public InventoryMovementController(
            InventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    @PostMapping
    public ResponseEntity<InventoryMovementDTO> create(
            @RequestBody InventoryMovementDTO inventoryMovementDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryMovementService.create(inventoryMovementDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryMovementService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryMovementDTO>> getAll() {
        return ResponseEntity.ok(inventoryMovementService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryMovementDTO> update(
            @PathVariable Long id,
            @RequestBody InventoryMovementDTO inventoryMovementDTO) {

        return ResponseEntity.ok(
                inventoryMovementService.update(id, inventoryMovementDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}