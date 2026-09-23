package com.delicious_cake.delicious_cake_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandDTO {

    private Long id;
    private Integer number;
    private Integer persons;
    private Boolean isAvailable;
}