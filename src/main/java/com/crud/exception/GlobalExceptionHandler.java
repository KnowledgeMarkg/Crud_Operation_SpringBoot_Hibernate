package com.crud.exception;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	//MethodArgumentNotValidException
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgException(MethodArgumentNotValidException ex){
		HashMap<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach((fieldError) -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
		return ResponseEntity.badRequest().body(errorMap);
	}
	
	@ExceptionHandler(value = DataNotFoundException.class)
	public ResponseEntity<Object> dataNotFound(DataNotFoundException ex){	
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
