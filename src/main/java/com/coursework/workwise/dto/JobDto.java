package com.coursework.workwise.dto;

import com.coursework.workwise.entity.Company;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Job}
 */
public record JobDto(Long id, String title, String description, String location, String salaryRange, String postedDate,
                     Company companyName) implements Serializable {
}