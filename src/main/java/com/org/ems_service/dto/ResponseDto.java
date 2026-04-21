package com.org.ems_service.dto;

import java.util.List;

public class ResponseDto<E> {
	private String message;
	private List<E> data;

	public ResponseDto(String message) {
		super();
		this.message = message;
	}

	public ResponseDto() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<E> getData() {
		return data;
	}

	public void setData(List<E> data) {
		this.data = data;
	}

}
