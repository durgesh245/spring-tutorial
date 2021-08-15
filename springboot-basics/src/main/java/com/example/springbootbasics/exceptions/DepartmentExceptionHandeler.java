package com.example.springbootbasics.exceptions;

public class DepartmentExceptionHandeler extends Exception{
    public DepartmentExceptionHandeler() {
        super();
    }

    public DepartmentExceptionHandeler(String message) {
        super(message);
    }

    public DepartmentExceptionHandeler(String message, Throwable cause) {
        super(message, cause);
    }

    public DepartmentExceptionHandeler(Throwable cause) {
        super(cause);
    }

    protected DepartmentExceptionHandeler(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
