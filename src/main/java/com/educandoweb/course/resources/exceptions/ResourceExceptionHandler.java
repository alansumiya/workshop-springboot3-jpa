package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice  //vai interceptar as exceções para que essa classe possar executar determinado
//tratamento
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class) //anotação para que o método vai interceptar
	//qualquer exceção do tipo ResourceNotFoundException e vai fazer o tratamento que estiver
	//dentro do método
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataBaseException.class) //anotação para que o método vai interceptar
	//qualquer exceção do tipo ResourceNotFoundException e vai fazer o tratamento que estiver
	//dentro do método
	public ResponseEntity<StandardError> database (DataBaseException e, HttpServletRequest request){
		String error = "DataBase error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
