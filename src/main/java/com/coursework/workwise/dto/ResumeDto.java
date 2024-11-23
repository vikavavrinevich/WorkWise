package com.coursework.workwise.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Resume}
 */
public record ResumeDto(Long id, UserDto user, String summary, String skills, String experience,
                        String education) implements Serializable {
}