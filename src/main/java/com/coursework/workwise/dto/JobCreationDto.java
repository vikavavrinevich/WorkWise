package com.coursework.workwise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Job}
 */
public record JobCreationDto(@NotNull @Size(max = 70) @NotEmpty @NotBlank String title,
                             @NotNull @Size(max = 255) @NotEmpty @NotBlank String description,
                             @NotNull @Size(max = 70) @NotEmpty @NotBlank String location,
                             @NotNull Long salary,
                             @NotNull CompanyDto company) implements Serializable {
}