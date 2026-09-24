package com.delicious_cake.delicious_cake_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delicious_cake.delicious_cake_app.dtos.InventoryDTO;
import com.delicious_cake.delicious_cake_app.services.InventoryService;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO inventoryDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryService.create(inventoryDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(
            @PathVariable Long id,
            @RequestBody InventoryDTO inventoryDTO) {

        return ResponseEntity.ok(inventoryService.update(id, inventoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}