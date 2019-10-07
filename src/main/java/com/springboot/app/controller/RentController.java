package com.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.springboot.app.dto.RentDto;
import com.springboot.app.mapper.MapperService;
import com.springboot.app.model.Rent;
import com.springboot.app.service.RentService;

@RestController
@RequestMapping("/rent")
public class RentController {

	@Autowired
	private RentService rentService;
	@Autowired
	private MapperService<RentDto, Rent> mapper;

	@GetMapping("/{id}")
	public ResponseEntity<RentDto> getRent(@PathVariable("id") Integer idRent) {
		return ResponseEntity.ok().body(mapper.mapToDto(rentService.getById(idRent)));
	}

	@PostMapping
	public ResponseEntity<RentDto> createRent(@Valid @RequestBody RentDto rentDto) {
		return new ResponseEntity<RentDto>(mapper.mapToDto(rentService.create(mapper.mapToEntity(rentDto))),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRent(@PathVariable("id") Integer idRent) {
		rentService.delete(idRent);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<RentDto> putRent(@PathVariable("id") Integer idCar, @Valid @RequestBody RentDto carDto) {
		return new ResponseEntity<RentDto>(mapper.mapToDto(rentService.update(idCar, mapper.mapToEntity(carDto))),
				HttpStatus.OK);
	}

}
