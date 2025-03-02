package com.hulkhiretech.payments.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HttpServiceEngine {

	private RestClient restClient;
	
 
	public HttpServiceEngine(RestClient.Builder restClientBuilder)
	{
		this.restClient = restClientBuilder.build();
	}
	
/*
	1. Method: POST
	2. Endpoint: https://api.stripe.com/v1/checkout/sessions
	3. Header where Basic Auth Authorization is needed
		apikey, ""
	4. Header
		Content-Type application/x-www-form-urlencoded
	5. Body - urlencoded
*/
	
	
	public String makeHttpCall() 
	{
		log.info("\ninvoked makeHttpCall| restClient: "+restClient);
		
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setBasicAuth("sk_test_tR3PYbcVNZZ796tH88S4VQ2u","");  //3. Header
		httpHeaders.add("Content-Type", "application/x-www-form-urlencoded"); //4. Header
		
		/*
		 MyClass implements Consumer<HttpHeaders> {
	
		void accept(HttpHeaders headers) {
			safdasfas
		} 
		}
		*/
		
		//Object requestBody = null;
		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		
		requestBody.add("cancel_url", "https://example.com/success");
		requestBody.add("success_url", "https://example.com/success");
		
		requestBody.add("mode", "payment");
		requestBody.add("line_items[0][price_data][currency]", "usd");
		requestBody.add("line_items[0][quantity]", "1");
		requestBody.add("line_items[0][price_data][unit_amount]", "200");
		requestBody.add("line_items[0][price_data][product_data][name]", "Sample Product");

		
		
		//5. Body - urlencoded
		ResponseEntity<String> response = restClient.method(HttpMethod.POST)
			.uri("https://api.stripe.com/v1/checkout/sessions")
			.headers(headers ->	headers.addAll(httpHeaders))
			.body(requestBody)
			.retrieve()
			.toEntity(String.class);
			
		log.info("\n response: \n"+response.getBody());
		return "httpserviceEngine";
	}
}
