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
	
	
	public ResponseEntity<String> makeHttpCall(HttpRequest httpRequest) 
	{
		log.info("\ninvoked makeHttpCall| restClient: "+restClient);
		
		
		
		/*
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
		
		
		*/
		
		
		
		
		/*
		//5. Body - urlencoded
		ResponseEntity<String> response = restClient.method(HttpMethod.POST)
			.uri("https://api.stripe.com/v1/checkout/sessions")
			.headers(headers ->	headers.addAll(httpHeaders))
			.body(requestBody)
			.retrieve()
			.toEntity(String.class);
		*/
			
		
		
		ResponseEntity<String> response = restClient.method(httpRequest.getMethod())
				.uri(httpRequest.getUrl())
				.headers(headers ->	headers.addAll(httpRequest.getHeaders()))
				.body(httpRequest.getRequestBody())
				.retrieve()
				.toEntity(String.class);
		
		
		log.info("\n response: \n"+response.getBody());
		
		return response;
	}
}
