package com.coursework.workwise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Company}
 */
public record CompanyCreationDto(@NotNull @Size(max = 255) @NotEmpty @NotBlank String name,
                                 @NotNull @Size(max = 255) @NotEmpty @NotBlank String industry,
                                 @NotNull @Size(max = 255) @NotEmpty @NotBlank String location,
                                 @NotNull @Size(max = 255) @NotEmpty @NotBlank String description) implements Serializable {
}