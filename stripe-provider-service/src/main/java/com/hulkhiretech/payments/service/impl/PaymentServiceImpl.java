 package com.hulkhiretech.payments.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hulkhiretech.payments.dto.CreatePaymentDTO;
import com.hulkhiretech.payments.dto.PaymentDTO;
import com.hulkhiretech.payments.http.HttpServiceEngine;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

	private HttpServiceEngine httpServiceEngine;
	
	private Gson gson;
	
	@Autowired
	public PaymentServiceImpl(HttpServiceEngine httpServiceEngine, Gson gson) {
		this.httpServiceEngine = httpServiceEngine;
		this.gson = gson;
	}

	public PaymentServiceImpl(HttpServiceEngine httpServiceEngine)
	{
		this.httpServiceEngine = httpServiceEngine;
	}
	
	@Override
	public PaymentDTO createPayment(CreatePaymentDTO createPaymentDTO)
	{
		
		log.info("\n invoked  createPaymentDTO: "+ createPaymentDTO);
		
		//========================================================API endpoint 1
		//Stripe PSP create SEssioon API call
		//httpServiceEngine.makeHttpCall();
		
		
		ResponseEntity<String> response= httpServiceEngine.makeHttpCall();
		log.info("\n Response from httpserverengine: "+response);
		
		PaymentDTO paymentsDto = gson.fromJson(response.getBody(), PaymentDTO.class);
		
		log.info("\n\n Converted to DTO payments: "+paymentsDto);
		//return "\n returned from paymentserviceimpl \n" +response+ "\n";
		
		return paymentsDto;
	}
}
