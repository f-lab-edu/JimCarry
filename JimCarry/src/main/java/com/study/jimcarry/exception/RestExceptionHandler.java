package com.study.jimcarry.exception;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.study.jimcarry.api.CommonResponse;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Exception handle
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
	private static final Logger apilog = LoggerFactory.getLogger("API");
	
//	@ExceptionHandler({BindException.class}) 
//	public ResponseEntity<CommonResponse> handleBindException(HttpServletRequest request, BindException ex){ 	
//
//    	log.debug("BindException : {}", ex);		
//		String message = ex.getMessage();
//		BindingResult bindingResult = ex.getBindingResult();
//		List<FieldError> errors = bindingResult.getFieldErrors();
//		if (errors.size() > 0 && StringUtils.isNotBlank(errors.get(0).getDefaultMessage())) {
//			message = errors.get(0).getDefaultMessage();
//		}
//		apilog.info("{} {} {} ", 
//				request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/'))
//				,HttpStatus.BAD_REQUEST
//				, message);
//		
//		return new ResponseEntity<>(new CommonResponse(HttpStatus.BAD_REQUEST.value(), message), 
//				HttpStatus.OK);
//    } 
	
	@ExceptionHandler({MethodArgumentNotValidException.class}) 
	public ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex){ 	

    	log.debug("MethodArgumentNotValidException : {}", ex);		
		String message = ex.getMessage();
    	log.error("message: {}", message);		
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> errors = bindingResult.getFieldErrors();
		if (errors.size() > 0 && StringUtils.isNotBlank(errors.get(0).getDefaultMessage())) {
			message = errors.get(0).getDefaultMessage();
	    	log.error("message: {}", message);		
		}
		apilog.info("{} {} {} ", 
				request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/'))
				,HttpStatus.BAD_REQUEST
				, message);
		
		return new ResponseEntity<>(new CommonResponse(HttpStatus.BAD_REQUEST.value(), message), 
				HttpStatus.BAD_REQUEST);
    } 
	
	@ExceptionHandler({CustomException.class}) 
	public ResponseEntity<CommonResponse> handleTaException(HttpServletRequest request, CustomException ex){ 

    	log.error("GatewayException : {}", ex.getMessage());		
    	log.debug("GatewayException : {}", ex);		
    	apilog.info("{} {} {} ", request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')), 
				ex.getCode(), ex.getMessage());
		return new ResponseEntity<>(new CommonResponse(ex.getCode(), ex.getMessage()), 
				HttpStatus.BAD_REQUEST);
    }

	@ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleOtherException(HttpServletRequest request, Exception ex){
    	
    	log.error("Exception : {}", ex.getMessage());		
    	log.debug("Exception : {}", ex);		
    	apilog.info("{} {} {}", request.getRequestURI().substring(request.getRequestURI().lastIndexOf('/')), 
				HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
		return new ResponseEntity<>(new CommonResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
				ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }  

}