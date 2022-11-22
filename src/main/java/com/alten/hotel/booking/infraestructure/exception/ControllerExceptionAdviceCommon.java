package com.alten.hotel.booking.infraestructure.exception;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.WebRequest;

import com.alten.hotel.booking.domain.dto.ServiceErrorResponse;

// Handle RestClientException and UrlSyntaxException thrown by controllers that use RestTemplate
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) 
class ControllerExceptionAdviceCommon{

	private final Logger logger = LoggerFactory.getLogger(ControllerExceptionAdviceCommon.class);

	@ExceptionHandler(value = {
			HttpStatusCodeException.class  // Descendent of RestClientException
	})
	ResponseEntity<ServiceErrorResponse> handleHttpStatusExceptions(HttpStatusCodeException e) {
		if (e.getStatusCode().is5xxServerError()) {
			logger.error(e.getMessage(), e);
		}
		return new ResponseEntity<>(new ServiceErrorResponse(e.getStatusCode(), e.getMessage()), e.getStatusCode());
	}
	
	// 500 for unhandled exceptions
	@ExceptionHandler(value = {
			RuntimeException.class,  // Includes RestClientException.class
			URISyntaxException.class 
	})
	ResponseEntity<ServiceErrorResponse> handle500Exceptions(Exception e) {
		// Log server error with stack trace 
		logger.error(e.getMessage(), e);
		return new ResponseEntity<>(new ServiceErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
		
		List<String> listErrorFields = e.getBindingResult().getFieldErrors().stream()
															.map(DefaultMessageSourceResolvable::getDefaultMessage)
															.collect(Collectors.toList());
		
		return new ResponseEntity<>(new ServiceErrorResponse(HttpStatus.BAD_REQUEST, 
				listErrorFields.toString()), HttpStatus.BAD_REQUEST);
    }
	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ServiceErrorResponse> handleException(MissingServletRequestParameterException exception) {
		return new ResponseEntity<>(new ServiceErrorResponse(HttpStatus.BAD_REQUEST, 
				exception.getMessage()), HttpStatus.BAD_REQUEST);
	}
    
	
	
	
}
