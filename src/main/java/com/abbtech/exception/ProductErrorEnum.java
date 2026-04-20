package com.abbtech.exception;


import com.abbtech.exception.base.BaseErrorService;

/**
 * Enum representing a set of sample error types.
 * Each error includes a specific error code, message, and corresponding HTTP status code.
 * Implements the {@link BaseErrorService} interface.
 */
public enum ProductErrorEnum implements BaseErrorService {

    /**
     * Error representing unauthorized access.
     */
    ITEM_NOT_FOUND("PRODUCT_NOT_FOUND-ERROR-0001", "PRODUCT_NOT_FOUND", 400),

    /**
     * Custom error description case error.
     */
    PRODUCT_PRISE_NOT_ALLOWED("PRODUCT_PRISE_NOT_ALLOWED-0002", "PRODUCT_PRISE_NOT_ALLOWED", 400),


    BRAND_NOT_FOUND("BRAND_NOT_FOUND-ERROR-0003", "BRAND_NOT_FOUND", 400);


    /**
     * Error message.
     */
    final String message;

    /**
     * HTTP status code corresponding to the error.
     */
    final int httpStatus;

    /**
     * Unique error code.
     */
    final String errorCode;

    /**
     * Constructor for the ProductErrorEnum.
     *
     * @param errorCode  The unique error code.
     * @param message    The error message.
     * @param httpStatus The HTTP status code associated with the error.
     */
    ProductErrorEnum(String errorCode, String message, int httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    /**
     * Gets the error message.
     *
     * @return The error message.
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Gets the HTTP status code.
     *
     * @return The HTTP status code.
     */
    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * Gets the unique error code.
     *
     * @return The error code.
     */
    @Override
    public String getErrorCode() {
        return errorCode;
    }
}

