package com.hulkhiretech.payments.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.Gson;
import com.hulkhiretech.payments.dto.CreatePaymentDTO;
import com.hulkhiretech.payments.dto.PaymentDTO;
import com.hulkhiretech.payments.http.HttpRequest;
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
		
		
		
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setBasicAuth("sk_test_tR3PYbcVNZZ796tH88S4VQ2u","");  //3. Header
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded"); //4. Header
		
	
		
		//Object requestBody = null;
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		
		requestBody.add("cancel_url", "https://example.com/success");
		requestBody.add("success_url", "https://example.com/success");
		
		requestBody.add("mode", "payment");
		requestBody.add("line_items[0][price_data][currency]", "usd");
		requestBody.add("line_items[0][quantity]", "1");
		requestBody.add("line_items[0][price_data][unit_amount]", "200");
		requestBody.add("line_items[0][price_data][product_data][name]", "Sample Product");

		
		HttpRequest httpRequest = HttpRequest.builder()
				.method(HttpMethod.POST)
				.url("https://api.stripe.com/v1/checkout/sessions")
				.headers(httpHeaders)
				.requestBody(requestBody)
				.build();
		
		
		ResponseEntity<String> response= httpServiceEngine.makeHttpCall(httpRequest);
		log.info("\n Response from httpserverengine: "+response);
		
		PaymentDTO paymentsDto = gson.fromJson(response.getBody(), PaymentDTO.class);
		
		log.info("\n\n Converted to DTO payments: "+paymentsDto);
		//return "\n returned from paymentserviceimpl \n" +response+ "\n";
		
		return paymentsDto;
	}
}
