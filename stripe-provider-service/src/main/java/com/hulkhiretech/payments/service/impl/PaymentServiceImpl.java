package com.hulkhiretech.payments.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.google.gson.Gson;
import com.hulkhiretech.payments.constants.Constants;
import com.hulkhiretech.payments.dto.CreatePaymentDTO;
import com.hulkhiretech.payments.dto.PaymentDTO;
import com.hulkhiretech.payments.exception.StripeProviderException;
import com.hulkhiretech.payments.http.HttpRequest;
import com.hulkhiretech.payments.http.HttpServiceEngine;
import com.hulkhiretech.payments.pojo.LineItem;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	private HttpServiceEngine httpServiceEngine;

	private Gson gson;

	@Value("${stripe.apikey}")
	private String apiKey;

	@Value("${stripe.create-session.url}")
	private String createSessionUrl;
	
	@Value("${stripe.get-session.url}")
	private String getSessionUrl;

	public PaymentServiceImpl(HttpServiceEngine httpServiceEngine, 
			Gson gson) {
		this.httpServiceEngine = httpServiceEngine;
		this.gson = gson;
	}

	@Override
	public PaymentDTO createPayment(CreatePaymentDTO createPaymentDTO) {
		log.info("invoked createPayment||createPaymentDTO:" + createPaymentDTO);

		/*
		if(createPaymentDTO.getSuccessUrl() == null) {//TODO temp
			log.error("Throwing error");
			throw new StripeProviderException
			("30001", "This is a test error message.");
		}
		*/
		
		
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setBasicAuth(apiKey, Constants.EMPTY_STRING);
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);

		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add(Constants.MODE, Constants.MODE_PAYMENT);

		requestBody.add(Constants.SUCCESS_URL, createPaymentDTO.getSuccessUrl());
		requestBody.add(Constants.CANCEL_URL, createPaymentDTO.getCancelUrl());

		for(int i = 0; i < createPaymentDTO.getLineItems().size(); i++) {
			LineItem lineItem = createPaymentDTO.getLineItems().get(i);

			requestBody.add(String.format(Constants.LINE_ITEMS_QUANTITY, i), 
					String.valueOf(lineItem.getQuantity()));
			requestBody.add(String.format(Constants.LINE_ITEMS_CURRENCY, i), 
					lineItem.getCurrency());
			requestBody.add(String.format(Constants.LINE_ITEMS_PRODUCT_NAME, i), 
					lineItem.getProductName());
			requestBody.add(String.format(Constants.LINE_ITEMS_UNIT_AMOUNT, i), 
					String.valueOf(lineItem.getUnitAmount()));
		}

		HttpRequest httpRequest = HttpRequest.builder()
				.method(HttpMethod.POST)
				.url(createSessionUrl)
				.headers(httpHeaders)
				.requestBody(requestBody)
				.build();

		ResponseEntity<String> response = httpServiceEngine.makeHttpCall(httpRequest);
		log.info("Response from httpServiceEngine:" + response);

		PaymentDTO paymentDto = gson.fromJson(response.getBody(), PaymentDTO.class);
		log.info("Converted to DTO paymentDTO:" + paymentDto);

		return paymentDto;
	}

	@Override
	public PaymentDTO getPayment(String id) {
		log.info("invoked getPayment|| id:" + id);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth(apiKey, Constants.EMPTY_STRING);
		httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

		HttpRequest httpRequest = HttpRequest.builder()
				.method(HttpMethod.GET)
				.url(getSessionUrl.replace(Constants.SESSION_ID, id))
				.headers(httpHeaders)
				.requestBody(Constants.EMPTY_STRING)
				.build();
		
		log.info("Passing httpRequest:" + httpRequest);

		ResponseEntity<String> response = httpServiceEngine.makeHttpCall(httpRequest);
		log.info("Response from httpServiceEngine:" + response);

		PaymentDTO paymentDto = gson.fromJson(response.getBody(), PaymentDTO.class);
		log.info("Converted to DTO paymentDTO:" + paymentDto);

		return paymentDto;
	}

	@Override
	public PaymentDTO expirePayment(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
