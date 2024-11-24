package com.coursework.workwise.repository;

import com.coursework.workwise.dto.UserDto;
import com.coursework.workwise.entity.Resume;
import com.coursework.workwise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    boolean existsByUser(User user);
    Optional<Resume> findByUser(User user);

}
