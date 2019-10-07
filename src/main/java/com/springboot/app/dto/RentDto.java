package com.springboot.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "id", "user", "car", "startDate", "endDate", "price" })
@ScriptAssert(lang = "javascript", script = "_.startDate <= _.endDate", alias = "_", message = "date of death cannot be before date of birth")
public class RentDto {

	private Integer id;

	private UserDto user;

	private CarDto car;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@Digits(integer = 10, fraction = 2)
	private Double price;

}
