package com.hulkhiretech.payments.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StripeProviderException extends RuntimeException {

	private final String errorCode;
    private final String errorMessage;

    public StripeProviderException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
