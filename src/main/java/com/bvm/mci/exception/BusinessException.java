package com.bvm.mci.exception;

public class BusinessException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 5947821275169999933L;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
