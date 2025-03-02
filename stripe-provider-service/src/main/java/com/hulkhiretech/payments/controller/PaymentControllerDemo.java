package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionExpireParams;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/v11/payments")
public class PaymentControllerDemo {

	
	//http://localhost:8083/v1/payments
	@PostMapping()
	public String createPayment()
	{
		try
		{
			Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";

			  SessionCreateParams params = SessionCreateParams.builder()
		                .setMode(SessionCreateParams.Mode.PAYMENT)
		                .setSuccessUrl("https://example.com/success")
		                .setCancelUrl("https://example.com/cancel")
		                .addLineItem(
		                        SessionCreateParams.LineItem.builder()
		                                .setQuantity(10L)
		                                .setPriceData(
		                                        SessionCreateParams.LineItem.PriceData.builder()
		                                                .setCurrency("usd")
		                                                .setUnitAmount(5000L) // $50.00 in cents
		                                                .setProductData(
		                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
		                                                                .setName("Sample Payment")
		                                                                .build()
		                                                )
		                                                .build()
		                                )
		                                .build()
		                )
		                .build();


			Session session = Session.create(params);
			 log.info("\n Payment session created, URL: " + session.getUrl());
	         log.info ("\n" +session.getUrl());
		}
		
		catch(Exception e)
		{
			log.info("Exception raised");
		}
		
         

		return "create payment method is invoked";
	}
	
	
	//http://localhost:8083/v1/payments/{id}
	@GetMapping("/{id}")
	public String getPaymentStatus(@PathVariable String id)
	{
		
		
		try
		{
			Session session =
					  Session.retrieve(
					    id
					  );
			log.info("\n Status:"+session.getStatus());
			log.info("\nPayment Status:"+session.getPaymentStatus());
			
			
		}
		catch(Exception e)
		{
			log.info("Exception raised");
		}
		return "get payemtn status";
	}
	
	
	
	
	
	//http://localhost:8083/v1/payments/id/expire
	@PostMapping("/{id}/expire")
	public String expirePayment(@PathVariable String id)
	{
		try
		{
			
			Session resource =
					  Session.retrieve(
					    id
					  );
					SessionExpireParams params = SessionExpireParams.builder().build();
					Session session = resource.expire(params);
			
		}
		catch(Exception e)
		{
			log.info("Exception raised");
		}
		
		
		return "expire payment method is invoked";
	}
	
	
}
