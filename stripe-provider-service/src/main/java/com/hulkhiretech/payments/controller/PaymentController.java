package com.hulkhiretech.payments.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkhiretech.payments.pojo.CreatePaymentReq;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/payments")
@Slf4j
public class PaymentController {

    @PostMapping
    public String createPayment(@RequestBody CreatePaymentReq createPaymentReq) {
        log.info("\n invoked createPayment||createPaymentReq: "+createPaymentReq);
        return "Payment Created Successfully";
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
