package com.coursework.workwise.repository;

import com.coursework.workwise.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
