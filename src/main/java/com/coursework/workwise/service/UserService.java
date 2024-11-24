package com.coursework.workwise.service;

import com.coursework.workwise.dto.UserDto;
import com.coursework.workwise.entity.User;
import com.coursework.workwise.enums.Role;
import com.coursework.workwise.exception.UserNotFoundException;
import com.coursework.workwise.mapper.UserMapper;
import com.coursework.workwise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this username already exists");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }

        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));

    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public UserDto getById(Long id){
        User user = repository
                .findById(id).orElseThrow(() -> new UserNotFoundException("User wit id " + id + "not found!"));
        return userMapper.toDto(user);
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Transactional
    public void changeRoleToEmployer(Long id){
        User user = repository
                .findById(id).orElseThrow(() -> new UserNotFoundException("User wit id " + id + "not found!"));
        user.setRole(Role.ROLE_EMPLOYER);
        save(user);
    }

}