package com.springboot.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "id", "user", "car", "startDate", "endDate", "price" })
public class RentDto {

	private Integer id;

	private UserDto user;

	private CarDto car;

	@NotNull(message = "{rent.car.date.start.notnull}")
	@NotBlank(message = "{rent.car.date.start.notblank}")
	private LocalDate startDate;

	@NotNull(message = "{rent.car.date.start.notnull}")
	@NotBlank(message = "{rent.car.date.start.notblank}")
	private LocalDate endDate;

	@Digits(integer = 10, fraction = 2)
	private Double price;

}
