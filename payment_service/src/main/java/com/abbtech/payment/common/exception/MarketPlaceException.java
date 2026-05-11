package com.abbtech.payment.common.exception;

public class MarketPlaceException extends RuntimeException {

    private final MarketPlaceErrorEnum error;

    public MarketPlaceException(MarketPlaceErrorEnum error) {
        super(error.getMessage());
        this.error = error;
    }

    public MarketPlaceErrorEnum getError() {
        return error;
    }
}
