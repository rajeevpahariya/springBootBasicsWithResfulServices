package com.rest.webservices.restfulwebservices.harcore.data;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		ExceptionBean exceptionBean = 
				new ExceptionBean(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionBean, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(StaticUserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(StaticUserNotFoundException ex
			, WebRequest request){
		ExceptionBean exceptionBean = 
				new ExceptionBean(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionBean, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionBean exceptionBean = 
				new ExceptionBean(new Date(), "Input parameters are not valid", 
						ex.getBindingResult().toString());
		return new ResponseEntity<Object>(exceptionBean, HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public final
	 * ResponseEntity<Object>
	 * handleValidateMethodArgsException(MethodArgumentNotValidException ex ,
	 * WebRequest request){ ExceptionBean exceptionBean = new ExceptionBean(new
	 * Date(), "Input parameters are not valid", ex.getBindingResult().toString());
	 * return new ResponseEntity<Object>(exceptionBean, HttpStatus.BAD_REQUEST); }
	 */
}
