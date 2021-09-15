package com.pandemic.system.pandemic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pandemic.system.pandemic.entities.HospitalErrorResponse;
import com.pandemic.system.pandemic.exceptions.HospitalNotFoundException;

@ControllerAdvice
public class HospitalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<HospitalErrorResponse> handleException(HospitalNotFoundException exc){		
		HospitalErrorResponse error = new HospitalErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<HospitalErrorResponse>(error, HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler
	public ResponseEntity<HospitalErrorResponse> handleException(Exception exc){
		HospitalErrorResponse error = new HospitalErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<HospitalErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
}