package com.springboot.app.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.dto.CarDto;
import com.springboot.app.dto.CarProfitDto;
import com.springboot.app.mapper.MapperService;
import com.springboot.app.model.Car;
import com.springboot.app.model.Rent;
import com.springboot.app.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {

	@Autowired
	private CarService carService;
	@Autowired
	private MapperService<CarDto, Car> mapper;

	@GetMapping("/{id}")
	public ResponseEntity<CarDto> getCar(@PathVariable("id") Integer idCar) {
		return ResponseEntity.ok().body(mapper.mapToDto(carService.getById(idCar)));
	}

	@PostMapping
	public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarDto carDto) {
		return new ResponseEntity<CarDto>(mapper.mapToDto(carService.create(mapper.mapToEntity(carDto))),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer idCar) {
		carService.delete(idCar);
		return ResponseEntity.ok().build();
	}

	@GetMapping("")
	public ResponseEntity<Page<CarDto>> getAllCar(@PageableDefault(page = 0, value = 10) Pageable pageable) {
		return new ResponseEntity<Page<CarDto>>(mapper.mapPageToDto(carService.getPage(pageable)), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarDto> putCar(@PathVariable("id") Integer idCar, @Valid @RequestBody CarDto carDto) {
		return new ResponseEntity<CarDto>(mapper.mapToDto(carService.update(idCar, mapper.mapToEntity(carDto))),
				HttpStatus.OK);
	}

	@GetMapping("/{id}/rents")
	public ResponseEntity<Page<Rent>> getRents(@PathVariable("id") Integer idCar, Pageable pageable) {
		return new ResponseEntity<>(carService.getRents(idCar, pageable), HttpStatus.OK);
	}

	/*
	@GetMapping("/{id}/rents/{start}/{end}")
	public ResponseEntity<Double> getProfit(@PathVariable("id") Integer idCar,
			@PathVariable("start")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, 
			@PathVariable("end")   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		return new ResponseEntity<ResponseData>(new ResponseData(carService.title(idCar)
				+ carService.getProfitByDateRange(idCar, startDate, endDate)), HttpStatus.OK);
	}
	*/
	
	@GetMapping("/{id}/rents/{start}/{end}")
	public ResponseEntity<CarProfitDto> getProfit(@PathVariable("id") Integer idCar,
			@PathVariable("start")  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, 
			@PathVariable("end")   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		
		CarProfitDto carProfit = new CarProfitDto();   
		carProfit.setTitle(carService.getById(idCar).getBrand() + " " + carService.getById(idCar).getModel());
		carProfit.setPrice(carService.getProfitByDateRange(idCar, startDate, endDate));
		
		return new ResponseEntity<CarProfitDto>(carProfit, HttpStatus.OK);
	}

}
