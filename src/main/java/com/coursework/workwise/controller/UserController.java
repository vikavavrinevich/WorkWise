package com.coursework.workwise.controller;

import com.coursework.workwise.dto.UserDto;
import com.coursework.workwise.exception.UserNotFoundException;
import com.coursework.workwise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try{
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/change_role/{id}")
//    @PreAuthorize("hasAnyRole('ADMIN', 'JOBSEEKER')")
    public void changeRole(@PathVariable Long id){
        userService.changeRoleToEmployer(id);
    }


}
