package com.delicious_cake.delicious_cake_app.mappers;

import com.delicious_cake.delicious_cake_app.entities.ReservationEntity;
import com.delicious_cake.delicious_cake_app.dtos.ReservationDTO;

public class ReservationMapper {

    public static ReservationDTO toDTO(ReservationEntity entity) {
        if (entity == null) {
            return null;
        }

        ReservationDTO dto = new ReservationDTO();

        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPickupAt(entity.getPickupAt());
        dto.setTotal(entity.getTotal());
        dto.setPending(entity.getPending());

        if (entity.getCustomer() != null) {
            dto.setCustomerId(entity.getCustomer().getId());
        }

        return dto;
    }

    public static ReservationEntity toEntity(ReservationDTO dto) {
        if (dto == null) {
            return null;
        }

        ReservationEntity entity = new ReservationEntity();

        entity.setDescription(dto.getDescription());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setPickupAt(dto.getPickupAt());
        entity.setTotal(dto.getTotal());
        entity.setPending(dto.getPending());

        return entity;
    }
}