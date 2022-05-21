package com.mindtree.cabbooking.utility;

import java.time.LocalDate;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindtree.cabbooking.exception.CabBookingException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@Autowired
	Environment environment;
	
	final Log LOGGER=LogFactory.getLog(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(CabBookingException.class)
	public ResponseEntity<ErrorInfo> bookingExceptionHandler(CabBookingException exception)
	{
		String message=environment.getProperty(exception.getMessage(),exception.getMessage());
		LOGGER.error(message);
		ErrorInfo error=new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(message);
		error.setTimeStamp(LocalDate.now());
		return new ResponseEntity<>(error,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> validateException(Exception exception)
	{
		String message;
		if(exception instanceof MethodArgumentNotValidException)
		{
			MethodArgumentNotValidException exe=(MethodArgumentNotValidException)exception;
			message=exe.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));		
		}
		else
		{
			ConstraintViolationException exe=(ConstraintViolationException) exception;
			message=exe.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
		}
		LOGGER.error(message);
		ErrorInfo error=new ErrorInfo();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(message);
		error.setTimeStamp(LocalDate.now());
		return new ResponseEntity<>(error,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalException(Exception exception)
	{
		LOGGER.error(exception);
		String message=environment.getProperty("General.EXCEPTION_MESSAGE");
		ErrorInfo error=new ErrorInfo();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(message);
		error.setTimeStamp(LocalDate.now());
		return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
