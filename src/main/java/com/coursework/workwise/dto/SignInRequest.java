package com.coursework.workwise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    private String password;
}
