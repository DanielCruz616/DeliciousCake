package com.delicious_cake.delicious_cake_app.dtos;

import java.time.LocalDateTime;

import com.delicious_cake.delicious_cake_app.enums.MovementType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryMovementDTO {

    private Long id;
    private Long productId;
    private MovementType type;
    private Integer quantity;
    private LocalDateTime date;
}