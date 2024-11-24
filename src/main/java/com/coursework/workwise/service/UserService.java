package com.coursework.workwise.service;

import com.coursework.workwise.dto.UserCreationDto;
import com.coursework.workwise.dto.UserDto;
import com.coursework.workwise.entity.User;
import com.coursework.workwise.exception.UserNotFoundException;
import com.coursework.workwise.mapper.UserMapper;
import com.coursework.workwise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper userMapper;

//    public UserDto getById(Long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
//        return userMapper.toDto(user);
//    }
//
//    public List<UserDto> getAll(){
//        List<User> users = userRepository.findAll();
//        return  users.stream()
//                .map(userMapper::toDto)
//                .toList();
//    }
//
//    @Transactional
//    public UserDto create(UserCreationDto userCreationDto){
//        return userMapper.toDto(userRepository.save(userMapper.toEntity(userCreationDto)));
//    }

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
}