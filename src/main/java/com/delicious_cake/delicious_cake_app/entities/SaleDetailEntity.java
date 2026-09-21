package com.delicious_cake.delicious_cake_app.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Table(name = "sale_details")
public class SaleDetailEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private SaleEntity sale;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;
}
