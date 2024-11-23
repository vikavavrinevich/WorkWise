package com.coursework.workwise.exception;

public class CompanyAlreadyExistException extends RuntimeException {
    public CompanyAlreadyExistException(String message) {
        super(message);
    }
}
