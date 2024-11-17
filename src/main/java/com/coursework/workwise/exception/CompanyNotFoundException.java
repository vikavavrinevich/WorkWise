package com.coursework.workwise.exception;

import jakarta.persistence.EntityNotFoundException;

public class CompanyNotFoundException extends EntityNotFoundException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
