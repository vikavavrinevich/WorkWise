package com.coursework.workwise.exception;

import jakarta.persistence.EntityNotFoundException;

public class JobNotFoundException extends EntityNotFoundException {
    public JobNotFoundException(String message) {
        super(message);
    }
}
