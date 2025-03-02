package com.hulkhiretech.payments.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.dto.CreatePaymentDTO;
import com.hulkhiretech.payments.dto.PaymentDTO;
import com.hulkhiretech.payments.pojo.CreatePaymentReq;
import com.hulkhiretech.payments.pojo.PaymentRes;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
public class PaymentController {

	
	private PaymentService paymentService;
	//seelct and click - alt+shift+s and geenrate constructor with fields
	
	private ModelMapper modelMapper;
	
    public PaymentController(PaymentService paymentService, ModelMapper modelMapper) {
	    	
		this.paymentService = paymentService;
		this.modelMapper = modelMapper;
	}



	@PostMapping
    public ResponseEntity<PaymentRes> createPayment(@RequestBody CreatePaymentReq createPaymentReq) {
        log.info("\n invoked createPayment||createPaymentReq: "+createPaymentReq);
        
        
        CreatePaymentDTO paymentDTO = modelMapper.map(createPaymentReq, CreatePaymentDTO.class);
        
        log.info("\n Converted to DTO payment DTO: "+paymentDTO);
        
        PaymentDTO response = paymentService.createPayment(paymentDTO);
        
        //ResponseEntity responseEntity = new ResponseEntity("Payment Created Successfully" +response, HttpStatus.CREATED);
       // ResponseEntity.ok().body(response);
        
        PaymentRes paymentRes = modelMapper.map(response, PaymentRes.class);
        
        log.info("\n returnnig response payments: "+paymentRes);
        
        //return new ResponseEntity<>("Payment Created Successfully" +response, HttpStatus.CREATED);
        return new ResponseEntity<>(paymentRes, HttpStatus.CREATED);

    }
    
    
    @GetMapping("/{id}")
    public String getPayment(@PathVariable String id)
    {
    	log.info("\n invoked getpayment"+ id);
    	return "Payment fetched";
    } 
    
    
    @PostMapping("/{id}/expire")
    public String expirePayment(@PathVariable String id) {
        log.info("\n invoked expirePayment for id: " + id);
        return "Payment expired Successfully";
    }

}   
