package com.coursework.workwise.entity;

import com.coursework.workwise.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<JobApplication> jobApplications;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;

    @OneToMany(mappedBy = "user")
    private List<Resume> resumes;

    @OneToOne(mappedBy = "employer")
    private Company company;
}


