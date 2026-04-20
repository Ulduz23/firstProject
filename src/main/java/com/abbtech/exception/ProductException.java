package com.abbtech.exception;


import com.abbtech.exception.base.BaseErrorService;
import com.abbtech.exception.base.BaseException;

/**
 * Exception class representing custom exceptions related to sample error cases.
 * Inherits from {@link BaseException}, allowing it to utilize error details
 * from {@link BaseErrorService} and optional additional arguments for flexible error message formatting.
 */
public class ProductException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor for {@link ProductException} with a throwable and additional arguments.
     *
     * @param baseErrorService The error service providing error details.
     * @param throwable        The root cause of the exception.
     * @param args             Additional arguments for the error.
     */
    public ProductException(BaseErrorService baseErrorService, Throwable throwable, Object... args) {
        super(baseErrorService, throwable, args);
    }

    /**
     * Constructor for {@link ProductException} with additional arguments.
     *
     * @param baseErrorService The error service providing error details.
     * @param args             Additional arguments for the error.
     */
    public ProductException(BaseErrorService baseErrorService, Object... args) {
        super(baseErrorService, args);
    }
}

