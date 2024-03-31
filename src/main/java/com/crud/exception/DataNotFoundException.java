package com.crud.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {
	public DataNotFoundException(String string) {
		super(string);
	}
}
