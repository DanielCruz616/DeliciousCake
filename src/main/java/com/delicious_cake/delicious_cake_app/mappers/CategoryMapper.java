package com.delicious_cake.delicious_cake_app.mappers;

import com.delicious_cake.delicious_cake_app.entities.CategoryEntity;
import com.delicious_cake.delicious_cake_app.dtos.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO toDTO(CategoryEntity entity) {
        if (entity == null) {
            return null;
        }

        CategoryDTO dto = new CategoryDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    public static CategoryEntity toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }

        CategoryEntity entity = new CategoryEntity();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}