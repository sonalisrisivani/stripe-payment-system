package com.hulkhiretech.payments.pojo;

import java.util.List;

import lombok.Data;

@Data
public class CreatePaymentReq {

	private String successUrl;
	private String cancelUrl;
	private List<LineItem> lineItem;
}


/*
 this will be demo json data for this obj
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