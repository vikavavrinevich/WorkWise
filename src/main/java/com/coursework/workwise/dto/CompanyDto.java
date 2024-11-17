package com.coursework.workwise.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Company}
 */
public record CompanyDto(Long id, String name, String industry, String location,
                         String description) implements Serializable {
}