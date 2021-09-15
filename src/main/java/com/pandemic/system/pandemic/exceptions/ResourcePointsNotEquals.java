package com.pandemic.system.pandemic.exceptions;

public class ResourcePointsNotEquals extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourcePointsNotEquals(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourcePointsNotEquals(String message) {
		super(message);
	}

	public ResourcePointsNotEquals(Throwable cause) {
		super(cause);
	}
}
