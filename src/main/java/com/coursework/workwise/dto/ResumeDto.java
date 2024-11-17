package com.coursework.workwise.dto;

import com.coursework.workwise.entity.User;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.Resume}
 */
public record ResumeDto(Long id, User userName, String summary, String skills, String experience,
                        String education) implements Serializable {
}