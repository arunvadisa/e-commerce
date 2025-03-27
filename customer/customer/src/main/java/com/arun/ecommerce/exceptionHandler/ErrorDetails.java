package com.arun.ecommerce.exceptionHandler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	Date timestamp;
	Integer status;
	String error;
	String errorMessage;
	
	
}
