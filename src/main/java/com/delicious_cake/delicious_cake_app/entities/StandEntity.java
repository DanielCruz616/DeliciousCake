package com.delicious_cake.delicious_cake_app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter 
@Setter 
@Table(name = "restaurant_tables")
public class StandEntity extends BaseEntity {
    
    @Column(nullable = false, unique = true)
    private Integer number;

    private Integer persons;
    
    @Column (nullable = false)
    private Boolean isAvailable;

    @OneToMany(mappedBy = "table")
    private List<SaleEntity> sales = new ArrayList<>();
}