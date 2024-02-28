package com.example.springreactproductapp.exception;

public class BusinessException extends RuntimeException{
    public BusinessException() {
    }
    public BusinessException(String s) {
        super(s);
    }
}
