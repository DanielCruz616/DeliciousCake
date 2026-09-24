package com.delicious_cake.delicious_cake_app.mappers;

import com.delicious_cake.delicious_cake_app.entities.InventoryEntity;
import com.delicious_cake.delicious_cake_app.dtos.InventoryDTO;

public class InventoryMapper {

    public static InventoryDTO toDTO(InventoryEntity entity) {
        if (entity == null) {
            return null;
        }

        InventoryDTO dto = new InventoryDTO();

        dto.setId(entity.getId());
        dto.setQuantity(entity.getQuantity());

        if (entity.getProduct() != null) {
            dto.setProductId(entity.getProduct().getId());
        }

        return dto;
    }

    public static InventoryEntity toEntity(InventoryDTO dto) {
        if (dto == null) {
            return null;
        }

        InventoryEntity entity = new InventoryEntity();

        entity.setQuantity(dto.getQuantity());

        return entity;
    }
}