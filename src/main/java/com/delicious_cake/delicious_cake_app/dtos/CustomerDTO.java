package com.delicious_cake.delicious_cake_app.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private Integer cc;
    private String name;
    private String lastName;
    private String email;
}