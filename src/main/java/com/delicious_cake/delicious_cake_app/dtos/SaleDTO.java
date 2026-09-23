package com.delicious_cake.delicious_cake_app.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDTO {

    private Long id;
    private Long customerId;
    private Long tableId;
    private LocalDateTime createdAt;
    private BigDecimal total;
    private List<SaleDetailDTO> details;
}