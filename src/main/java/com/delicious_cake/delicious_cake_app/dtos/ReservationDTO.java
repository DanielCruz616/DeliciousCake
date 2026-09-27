package com.delicious_cake.delicious_cake_app.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDTO {

    private Long id;
    private Long customerId;
    private String description;
    private LocalDate createdAt;
    private BigDecimal total;
    private BigDecimal pending;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate pickupAt;
    
}