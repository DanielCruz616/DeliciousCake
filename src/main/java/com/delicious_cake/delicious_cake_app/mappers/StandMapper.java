package com.delicious_cake.delicious_cake_app.mappers;

import com.delicious_cake.delicious_cake_app.entities.StandEntity;
import com.delicious_cake.delicious_cake_app.dtos.StandDTO;

public class StandMapper {

    public static StandDTO toDTO(StandEntity entity) {
        if (entity == null) {
            return null;
        }

        StandDTO dto = new StandDTO();

        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setPersons(entity.getPersons());
        dto.setIsAvailable(entity.getIsAvailable());

        return dto;
    }

    public static StandEntity toEntity(StandDTO dto) {
        if (dto == null) {
            return null;
        }

        StandEntity entity = new StandEntity();

        entity.setNumber(dto.getNumber());
        entity.setPersons(dto.getPersons());
        entity.setIsAvailable(dto.getIsAvailable());

        return entity;
    }
}