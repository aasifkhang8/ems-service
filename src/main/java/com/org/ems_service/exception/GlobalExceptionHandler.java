/**
 * 
 */
package com.org.ems_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
//		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), true, HttpStatus.NOT_FOUND);
		Map map = new HashMap<>();
		map.put("message", ex.getMessage());
		map.put("success", false);
		map.put("status", HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> ExceptionHandler(Exception ex) {
//		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), true, HttpStatus.NOT_FOUND);
		Map map = new HashMap<>();
		map.put("message", (ex.getMessage() != null && ex.getMessage().trim().length() > 0) 
				? ex.getMessage()
				: "Something Wrong On Server");
		map.put("success", false);
		map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
}
