package com.coursework.workwise.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.coursework.workwise.entity.Job}
 */
public record JobDto(Long id, String title, String description, String location, Long salary, CompanyDto companyName, LocalDate postedDate) implements Serializable {
  }