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
	public ResponseEntity<RentDto> getCar(@PathVariable("id") Integer idRent) {
		return ResponseEntity.ok().body(mapper.mapToDto(rentService.getById(idRent)));
	}

	@PostMapping
	public ResponseEntity<RentDto> createCar(@Valid @RequestBody RentDto rentDto) {
		return new ResponseEntity<RentDto>(mapper.mapToDto(rentService.create(mapper.mapToEntity(rentDto))),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer idRent) {
		rentService.delete(idRent);
		return ResponseEntity.ok().build();
	}
	/*
	 * @GetMapping("") public ResponseEntity<Page<RentDto>>
	 * getAllCar(@PageableDefault(page = 0, value = 10) Pageable pageable) { return
	 * new ResponseEntity<Page<RentDto>>(mapper.mapPageToDto(rentService.getPage(
	 * pageable)), HttpStatus.OK); }
	 */

	@PutMapping("/{id}")
	public ResponseEntity<RentDto> putCar(@PathVariable("id") Integer idCar, @Valid @RequestBody RentDto carDto) {
		return new ResponseEntity<RentDto>(mapper.mapToDto(rentService.update(idCar, mapper.mapToEntity(carDto))),
				HttpStatus.OK);
	}
	/*
	 * @GetMapping("/{id}/users") public ResponseEntity<Page<Rent>>
	 * getRents(@PathVariable("id") Integer idCar, Pageable pageable) { return new
	 * ResponseEntity<>(rentService.getRents(idCar, pageable), HttpStatus.OK); }
	 * 
	 * 
	 * @PathVariable("idCar") Integer idCar, @Valid @RequestBody RentDto rentDto)
	 * throws NotFoundException { return new
	 * ResponseEntity<RentDto>(mapper.mapToDto(rentService.createRentService(idUser,
	 * idCar, rentDto)), HttpStatus.OK); }
	 * 
	 * @GetMapping public ResponseEntity<Page<RentDto>>
	 * getAllRent(@PathVariable("idUser") Integer idUser,
	 * 
	 * @PathVariable("idCar") Integer idCar, @PageableDefault(page = 0, value = 10)
	 * Pageable pageable) { return new
	 * ResponseEntity<>(mapper.mapPageToDto(rentService.getAllRentService(idUser,
	 * idCar, pageable)), HttpStatus.OK); }
	 * 
	 * @DeleteMapping("/{idRent}") public ResponseEntity<?>
	 * deleteRent(@PathVariable("idRent") Integer idRent) throws NotFoundException {
	 * rentService.deleteRentService(idRent); return ResponseEntity.ok().build(); }
	 * 
	 * @PutMapping("/{idRent}") public ResponseEntity<RentDto>
	 * putRent(@PathVariable("idRent") Integer idRent, @Valid @RequestBody RentDto
	 * rentDto) throws NotFoundException, DateFormatNoValidException { return new
	 * ResponseEntity<>(mapper.mapToDto(rentService.updateRentService(idRent,
	 * rentDto)), HttpStatus.OK); }
	 */
}
