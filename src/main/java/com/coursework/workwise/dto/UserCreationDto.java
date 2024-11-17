package com.coursework.workwise.dto;

import com.coursework.workwise.enums.Role;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.User}
 */
public record UserCreationDto(@NotNull @Size(max = 255) @NotEmpty @NotBlank String name,
                              @NotNull @Email( regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") @NotEmpty @NotBlank String email,
                              @NotNull @Size(max = 255) @NotEmpty  @NotBlank String password,
                              @NotNull Role role) implements Serializable {
}