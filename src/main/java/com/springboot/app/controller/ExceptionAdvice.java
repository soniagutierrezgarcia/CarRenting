package com.springboot.app.controller;

import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springboot.app.exception.NotFoundException;

@ControllerAdvice(basePackages = "com.springboot.app")
public class ExceptionAdvice {

	private static final Integer NOT_FOUND_CODE = 404;

	@ResponseBody
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiError notFound(Exception e) {
		return new ApiError(NOT_FOUND_CODE, e.getClass().getSimpleName().concat(getExceptionMessage(e)));
	}

	@ResponseBody
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ApiError validationError(ValidationException e) {
		return new ApiError(422, e.getClass().getSimpleName().concat(getExceptionMessage(e)));
	}

	private String getExceptionMessage(Exception exception) {
		return Optional.ofNullable(exception).map(Exception::getMessage).orElse("");
	}
}