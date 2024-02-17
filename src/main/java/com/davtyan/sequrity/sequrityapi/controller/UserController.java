package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.dto.register.response.RoleResponse;
import com.davtyan.sequrity.sequrityapi.dto.register.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostMapping("/registration")
    public Long registration(@RequestBody UserRequest user) {
        return userService.register(user);
    }

    @PostMapping("/roles")
    public void addRoles(Long userId, List<Role> newRoles) {
        roleService.addRoles(userId, newRoles);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByUserName(@PathVariable("username") String username) {
        var user = userService.findByUserName(username);
        var userResponse = UserResponse.builder()
                .id(user.getId())
                .username(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(getRoles(user))
                .build();
        return ResponseEntity.ok(userResponse);
    }

    private List<RoleResponse> getRoles(User byUserName) {
        return byUserName.getRoles().stream().map(this::createRole).collect(Collectors.toList());
    }

    private RoleResponse createRole(Role role) {
        return RoleResponse.builder().name(role.getName()).build();
    }
}
