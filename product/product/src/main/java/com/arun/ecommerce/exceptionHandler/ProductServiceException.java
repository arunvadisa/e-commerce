package com.arun.ecommerce.exceptionHandler;

public class ProductServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductServiceException(String message,String stamp) {
		super(message);
	}

}
