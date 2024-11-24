package com.coursework.workwise.dto;

import com.coursework.workwise.enums.Role;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.User}
 */
public record UserDto(Long id, String username, String email, String password, Role role) implements Serializable {
}