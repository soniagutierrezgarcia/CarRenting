package com.springboot.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "id", "model", "brand" })
public class CarDto {

	private Integer id;

	@NotNull(message = "{car.model.notnull}")
	@NotBlank(message = "{car.model.notblank}")
	private String model;

	@NotNull(message = "{car.brand.notnull}")
	@NotBlank(message = "{car.brand.notblank}")
	private String brand;

}