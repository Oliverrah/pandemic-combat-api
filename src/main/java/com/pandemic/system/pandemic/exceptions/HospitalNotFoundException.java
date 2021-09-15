package com.pandemic.system.pandemic.exceptions;

public class HospitalNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public HospitalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HospitalNotFoundException(String message) {
		super(message);		
	}

	public HospitalNotFoundException(Throwable cause) {
		super(cause);
	}	
}