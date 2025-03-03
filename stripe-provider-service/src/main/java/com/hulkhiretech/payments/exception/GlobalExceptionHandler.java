package com.hulkhiretech.payments.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hulkhiretech.payments.pojo.ErrorRes;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(StripeProviderException.class)
    public ResponseEntity<ErrorRes> handleStripeProviderException(StripeProviderException ex) {
		log.error("StripeProviderException occurred:", ex);
		
		ErrorRes errorRes = new ErrorRes();
		errorRes.setErrorCode(ex.getErrorCode());
		errorRes.setErrorMessage(ex.getMessage());
		
        log.info("Returning errorRes:" + errorRes);
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }
}
