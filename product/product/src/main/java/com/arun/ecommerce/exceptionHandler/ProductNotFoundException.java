package com.arun.ecommerce.exceptionHandler;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -2295802405113946436L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
