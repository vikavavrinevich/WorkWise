package com.coursework.workwise.dto;

import com.coursework.workwise.entity.Job;
import com.coursework.workwise.entity.User;
import com.coursework.workwise.enums.ApplicationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.JobApplication}
 */
public record JobApplicationCreationDto(@NotNull JobDto job,
                                        @NotNull UserDto user) implements Serializable {
}