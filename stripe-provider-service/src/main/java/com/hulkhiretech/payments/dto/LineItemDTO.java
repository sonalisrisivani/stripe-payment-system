package com.hulkhiretech.payments.dto;

import lombok.Data;

//this file is to convert t=pojo obj into dto object before sending into service layer

@Data
public class LineItemDTO {

	private int quantity;
	private String currency;
	private String productName;
	private int unitAmount;
}
