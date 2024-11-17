package com.coursework.workwise.dto;

import com.coursework.workwise.entity.Job;
import com.coursework.workwise.entity.User;
import com.coursework.workwise.enums.ApplicationStatus;

import java.io.Serializable;

/**
 * DTO for {@link com.coursework.workwise.entity.JobApplication}
 */
public record JobApplicationDto(Long id, Job jobName, User userName, ApplicationStatus status,
                                String submittedDate) implements Serializable {
}