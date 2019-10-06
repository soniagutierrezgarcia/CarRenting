package com.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.springboot.app.dto.UserDto;
import com.springboot.app.mapper.MapperService;
import com.springboot.app.model.User;
import com.springboot.app.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private MapperService<UserDto, User> mapper;

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable("id") Integer idUser) {
		return ResponseEntity.ok().body(mapper.mapToDto(userService.getById(idUser)));
	}

	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto>(mapper.mapToDto(userService.create(mapper.mapToEntity(userDto))),
				HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer idUser) {
		userService.delete(idUser);
		return ResponseEntity.ok().build();
	}

	@GetMapping("")
	public ResponseEntity<Page<UserDto>> getAllUser(@PageableDefault(page = 0, value = 10) Pageable pageable) {
		return new ResponseEntity<Page<UserDto>>(mapper.mapPageToDto(userService.getPage(pageable)), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> putUser(@PathVariable("id") Integer idUser, @Valid @RequestBody UserDto userDto) {
		return new ResponseEntity<UserDto>(mapper.mapToDto(userService.update(idUser, mapper.mapToEntity(userDto))),
				HttpStatus.OK);
	}

}
