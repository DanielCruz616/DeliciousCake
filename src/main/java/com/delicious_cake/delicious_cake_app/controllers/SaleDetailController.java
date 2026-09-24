package com.delicious_cake.delicious_cake_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.delicious_cake.delicious_cake_app.dtos.SaleDetailDTO;
import com.delicious_cake.delicious_cake_app.services.SaleDetailService;

@RestController
@RequestMapping("/sale-details")
public class SaleDetailController {

    private final SaleDetailService saleDetailService;

    public SaleDetailController(SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @PostMapping
    public ResponseEntity<SaleDetailDTO> create(
            @RequestBody SaleDetailDTO saleDetailDTO) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saleDetailService.create(saleDetailDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(saleDetailService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SaleDetailDTO>> getAll() {
        return ResponseEntity.ok(saleDetailService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> update(
            @PathVariable Long id,
            @RequestBody SaleDetailDTO saleDetailDTO) {

        return ResponseEntity.ok(
                saleDetailService.update(id, saleDetailDTO)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}