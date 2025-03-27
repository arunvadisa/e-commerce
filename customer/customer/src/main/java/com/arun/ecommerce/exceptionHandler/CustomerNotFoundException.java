package com.arun.ecommerce.exceptionHandler;

public class CustomerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2295802405113946436L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
}
