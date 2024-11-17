package com.coursework.workwise.exception;

import jakarta.persistence.EntityNotFoundException;

public class JobNotFountException extends EntityNotFoundException {
    public JobNotFountException(String message) {
        super(message);
    }
}
