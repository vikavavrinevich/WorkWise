package com.coursework.workwise.exception;

import jakarta.persistence.EntityNotFoundException;

public class ResumeNotFoundException extends EntityNotFoundException {
    public ResumeNotFoundException(String message) {
        super(message);
    }
}
