package com.cg.osm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {      // Class for Handling all the Exceptions
	
	@ExceptionHandler(CartNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleCartNotFoundException(CartNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleCustomerNotFoundException(CustomerNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(OrderBillNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleOrderBillNotFoundException(OrderBillNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(ProductCategoryNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleProductNotFoundException(ProductCategoryNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(SweetItemNotFoundException.class)// Exception handler method for sweet item 
	public ResponseEntity<ExceptionResponse> handleSweetItemNotFoundException(SweetItemNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	@ExceptionHandler(SweetOrderNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleSweetOrderNotFoundException(SweetOrderNotFoundException e) {
		ExceptionResponse response=new ExceptionResponse();
        response.setErrorCode("CONFLICT");
        response.setErrorMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
		
	}
	
	
	
	
}
