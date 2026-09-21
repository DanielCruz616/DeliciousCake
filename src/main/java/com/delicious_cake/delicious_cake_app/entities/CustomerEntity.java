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
@Table(name = "customers")
public class CustomerEntity extends BaseEntity {

    @Column(unique = true)
    private Integer cc;

    private String name;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "customer")
    private List<SaleEntity> sales = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<ReservationEntity> reservations = new ArrayList<>();
}