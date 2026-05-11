package com.abbtech.payment.common.exception;

import org.springframework.http.HttpStatus;

public enum MarketPlaceErrorEnum {
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "Payment not found"),
    NOTIFICATION_CLIENT_ERROR(HttpStatus.BAD_GATEWAY, "Notification service request failed");

    private final HttpStatus status;
    private final String message;

    MarketPlaceErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
