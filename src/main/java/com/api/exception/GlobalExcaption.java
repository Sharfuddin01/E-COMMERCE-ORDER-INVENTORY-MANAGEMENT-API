package com.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.api.dto.ErrorDto;

@ControllerAdvice
public class GlobalExcaption {
	
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDto> runtimeException(RuntimeException e , WebRequest request){
		
		
		ErrorDto error=new ErrorDto();
		error.setMesaage(e.getMessage());
		error.setUrl(request.getDescription(true));
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
