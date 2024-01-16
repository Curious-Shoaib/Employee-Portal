package com.myApp.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.myApp.exception.ProjectException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	
	@Autowired
	Environment env;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception) {
		
		ErrorInfo ef=new ErrorInfo();
		ef.setErrorMessage(env.getProperty("GEN_EXCEPTION"));
		ef.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		ef.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(ef,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ProjectException.class)
	public ResponseEntity<ErrorInfo> projectExceptionHandler(ProjectException exception) {
		
		ErrorInfo ef=new ErrorInfo();
		ef.setErrorMessage(env.getProperty(exception.getMessage()));
		ef.setErrorCode(HttpStatus.NOT_FOUND.value());
		ef.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(ef,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		
		String errorMsg = exception.getBindingResult().getAllErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.joining(", "));
		errorInfo.setErrorMessage(errorMsg);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> pathExceptionHandler(ConstraintViolationException exception) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		String errorMsg = exception.getConstraintViolations().stream().map(x -> x.getMessage())
				.collect(Collectors.joining(", "));
		errorInfo.setErrorMessage(errorMsg);
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorInfo> resourceNotFoundException(NoResourceFoundException exception) {
		ErrorInfo errorInfo = new ErrorInfo();
		errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorInfo.setErrorMessage("No resource found, make a valid request");
		errorInfo.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
	} 
}
