package com.springboot.app.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "rent")
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	private User user;
	@ManyToOne
	private Car car;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private Double price;

}