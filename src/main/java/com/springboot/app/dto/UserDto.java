package com.springboot.app.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "id", "name" })
public class UserDto {

	private Integer id;

	@NotNull(message = "{user.nameuser.notnull}")
	@NotBlank(message = "{user.nameuser.notblank}")
	@Size(max = 50, message = "{user.nameuser.max}")
	private String name;

}