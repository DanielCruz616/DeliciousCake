package com.delicious_cake.delicious_cake_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delicious_cake.delicious_cake_app.dtos.StandDTO;
import com.delicious_cake.delicious_cake_app.services.StandService;

@RestController
@RequestMapping("/stands")
public class StandController {

    private final StandService standService;

    public StandController(StandService standService) {
        this.standService = standService;
    }

    @PostMapping
    public ResponseEntity<StandDTO> create(@RequestBody StandDTO standDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(standService.create(standDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(standService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<StandDTO>> getAll() {
        return ResponseEntity.ok(standService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StandDTO> update(
            @PathVariable Long id,
            @RequestBody StandDTO standDTO) {

        return ResponseEntity.ok(standService.update(id, standDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        standService.delete(id);
        return ResponseEntity.noContent().build();
    }
}