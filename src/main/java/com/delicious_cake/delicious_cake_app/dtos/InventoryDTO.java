package com.delicious_cake.delicious_cake_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDTO {

    private Long id;
    private Long productId;
    private Integer quantity;
}