 package com.hulkhiretech.payments.service.impl;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dto.CreatePaymentDTO;
import com.hulkhiretech.payments.http.HttpServiceEngine;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

	private HttpServiceEngine httpServiceEngine;
	
	public PaymentServiceImpl(HttpServiceEngine httpServiceEngine)
	{
		this.httpServiceEngine = httpServiceEngine;
	}
	
	@Override
	public String createPayment(CreatePaymentDTO createPaymentDTO)
	{
		
		log.info("\n invoked  createPaymentDTO: "+ createPaymentDTO);
		
		//========================================================API endpoint 1
		//Stripe PSP create SEssioon API call
		//httpServiceEngine.makeHttpCall();
		
		
		String response= httpServiceEngine.makeHttpCall();
		log.info("\n Response from httpserverengine: "+response);
		
		return "\n returned from paymentserviceimpl \n" +response+ "\n";
	}
}
