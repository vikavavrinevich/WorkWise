package com.coursework.workwise.exception;

import jakarta.persistence.EntityNotFoundException;

public class JobApplicationNotFoundException extends EntityNotFoundException {
    public JobApplicationNotFoundException(String message) {
        super(message);
    }
}
