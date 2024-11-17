package com.coursework.workwise.dto;

import com.coursework.workwise.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Resume}
 */
public record ResumeCreationDto(@NotNull User userName,
                                @NotNull @Size(max = 255) @NotEmpty @NotBlank String summary,
                                @NotNull @Size(max = 255) @NotEmpty @NotBlank String skills,
                                @NotNull @Size(max = 255) @NotEmpty @NotBlank String experience,
                                @NotNull @Size(max = 255) @NotEmpty @NotBlank String education) implements Serializable {
}