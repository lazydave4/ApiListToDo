package com.dawid;

public class OwnException extends RuntimeException {

    private ExceptionAlert exceptionAlert;

    public OwnException(ExceptionAlert exceptionAlert) {

        this.exceptionAlert = exceptionAlert;
    }
}
