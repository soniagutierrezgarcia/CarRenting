package com.springboot.app.dto;

import javax.validation.constraints.Digits;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.Data;

@Data
@JsonPropertyOrder(value = { "title", "price"})
public class CarProfitDto {
	
	private String title;
	
	@Digits(integer = 10, fraction = 2)
	private Double price;
}
