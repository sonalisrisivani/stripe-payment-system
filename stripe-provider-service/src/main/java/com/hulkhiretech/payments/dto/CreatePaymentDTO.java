package com.hulkhiretech.payments.dto;
//this file is to convert t=pojo obj into dto object before sending into service layer

import java.util.List;

import com.hulkhiretech.payments.pojo.LineItem;

import lombok.Data;

@Data
public class CreatePaymentDTO {

	private String successUrl;
	private String cancelUrl;
	private List<LineItem> lineItem;
}


/*
 this w ill be demo json data for this obj
 {
  "successUrl": "https://example.com/success",
  "cancelUrl": "https://example.com/cancel",
  "lineItem": [
    {
      "quantity": 2,
      "currency": "USD",
      "productName": "Wireless Mouse",
      "unitAmount": 25
    },
    {
      "quantity": 1,
      "currency": "USD",
      "productName": "Mechanical Keyboard",
      "unitAmount": 80
    }
  ]
}

 */