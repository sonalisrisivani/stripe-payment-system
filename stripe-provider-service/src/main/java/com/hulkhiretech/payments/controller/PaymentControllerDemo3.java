package com.hulkhiretech.payments.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionExpireParams;


@RestController
@RequestMapping("v1/checkout/sessions")
@Slf4j
public class PaymentControllerDemo3 {

	
	
	//create session
	//http://localhost:8083/v1/checkout/sessions
	@PostMapping()
	public String createSession()
	{
		
		
		Stripe.apiKey = "sk_test_51QqzH1Glj8Fd7cTPfdZrTl0XFMCSWJKq0na8xXUlg31lzTAfhRCl7faTNGkNuEGb7jNVlLqAetD0wiiXapZjaYIV00s61NKnwk";
		
		try
		{
			// Create a Stripe Checkout session
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
			log.info("\n url :" +session.getUrl());
			
		}
		
	    catch(Exception e)
		{
	    	log.info("Exception is raised");
	    	
		}

		return "create session invoked";
	}
	
	
	
	
	//retrive session status
	//http://localhost:8083/v1/checkout/sessions/:id
	@GetMapping("/{id}")
	public String retriveSession(@PathVariable String id)
	{
		
		
		try 
		{
			Session session =
					  Session.retrieve(
					    id
					  );	
			log.info("\n Status:" +session.getStatus());
			log.info("\n Payment Status:" +session.getPaymentStatus());
		}
		catch(Exception e)
		{
			log.info("Exception is raised");
	    	
		}
		return "retrive session invoked";
	}
	
	
	
	
	
	//exporing the session
	//http://localhost:8083/v1/checkout/sessions/:id/expire
	@PostMapping("/{id}/expire")
	public String expireSession(@PathVariable String id)
	{
		Stripe.apiKey = "sk_test_tR3PYbcVNZZ796tH88S4VQ2u";
		try 
		{
			Session resource =
					  Session.retrieve(
					    id
					  );
					SessionExpireParams params = SessionExpireParams.builder().build();
					Session session = resource.expire(params);
			log.info("\n this is expire session api");
			log.info("\n Status:"+session.getStatus());
			log.info("\n Payment Status:" +session.getPaymentStatus());
			
			
		}
		catch(Exception e)
		{
			log.info("Exception is raised");
	    	
		}
		return "expire session invoked";
	}
}
