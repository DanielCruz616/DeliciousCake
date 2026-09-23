package com.delicious_cake.delicious_cake_app.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

    private Long id;
    private Long customerId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime pickupAt;
    private BigDecimal total;
    private BigDecimal pending;
}