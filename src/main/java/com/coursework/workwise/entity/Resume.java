package com.coursework.workwise.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "resumes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "summary")
    private String summary;

    @Column(name = "skills")
    private String skills;

    @Column(name = "experience")
    private String experience;

    @Column(name = "education")
    private String education;
}