package com.delicious_cake.delicious_cake_app.mappers;

import com.delicious_cake.delicious_cake_app.entities.InventoryMovementEntity;
import com.delicious_cake.delicious_cake_app.dtos.InventoryMovementDTO;

public class InventoryMovementMapper {

    public static InventoryMovementDTO toDTO(InventoryMovementEntity entity) {
        if (entity == null) {
            return null;
        }

        InventoryMovementDTO dto = new InventoryMovementDTO();

        dto.setId(entity.getId());
        dto.setType(entity.getType());
        dto.setQuantity(entity.getQuantity());
        dto.setDate(entity.getDate());

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
        }

        return dto;
    }

    public static InventoryMovementEntity toEntity(InventoryMovementDTO dto) {
        if (dto == null) {
            return null;
        }

        InventoryMovementEntity entity = new InventoryMovementEntity();

        entity.setType(dto.getType());
        entity.setQuantity(dto.getQuantity());
        entity.setDate(dto.getDate());

        return entity;
    }
}