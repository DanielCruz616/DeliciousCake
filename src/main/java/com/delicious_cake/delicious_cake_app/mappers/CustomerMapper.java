package com.delicious_cake.delicious_cake_app.mappers;

import com.delicious_cake.delicious_cake_app.entities.CustomerEntity;
import com.delicious_cake.delicious_cake_app.dtos.CustomerDTO;

public class CustomerMapper {

    public static CustomerDTO toDTO(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }

        CustomerDTO dto = new CustomerDTO();

        dto.setId(entity.getId());
        dto.setCc(entity.getCc());
        dto.setName(entity.getName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());

        return dto;
    }

    public static CustomerEntity toEntity(CustomerDTO dto) {
        if (dto == null) {
            return null;
        }

        CustomerEntity entity = new CustomerEntity();

        entity.setCc(dto.getCc());
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        return entity;
    }
}