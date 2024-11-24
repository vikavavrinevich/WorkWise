package com.coursework.workwise.dto;

import com.coursework.workwise.enums.ApplicationStatus;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.JobApplication}
 */
public record JobApplicationDto(Long id, JobDto job, UserDto user, ApplicationStatus status) implements Serializable {
  }