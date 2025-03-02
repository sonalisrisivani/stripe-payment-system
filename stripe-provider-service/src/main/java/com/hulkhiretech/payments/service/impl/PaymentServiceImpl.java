 package com.hulkhiretech.payments.service.impl;

import org.springframework.stereotype.Service;

import com.hulkhiretech.payments.dto.CreatePaymentDTO;
import com.hulkhiretech.payments.service.interfaces.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService{

	@Override
	public String createPayment(CreatePaymentDTO createPaymentDTO)
	{
		log.info("\n invoked  createPaymentDTO: "+ createPaymentDTO);
		return null;
	}
}
