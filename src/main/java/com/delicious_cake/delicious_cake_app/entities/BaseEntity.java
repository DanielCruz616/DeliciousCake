package com.delicious_cake.delicious_cake_app.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import uk.co.jemos.podam.common.PodamExclude;


@Getter
@Setter 
@MappedSuperclass
public abstract class BaseEntity {

	@PodamExclude
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
