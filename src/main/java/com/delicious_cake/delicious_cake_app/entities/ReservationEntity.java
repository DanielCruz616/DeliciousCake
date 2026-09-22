package com.delicious_cake.delicious_cake_app.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@Table(name = "reservations")
public class ReservationEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    private String description;

    @Column (nullable = false)
    private LocalDateTime createdAt;

    @Column (nullable = false)
    private LocalDateTime pickupAt;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column (nullable = false, precision = 10, scale = 2)
    private BigDecimal pending;
}
