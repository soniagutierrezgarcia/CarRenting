package com.springboot.app.controller;

import java.io.Serializable;

public class ApiError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer status;
	private String errorMessage;

	public ApiError(Integer status, String errorMessage) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
