package com.davtyan.sequrity.sequrityapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.dto.register.response.RoleResponse;
import com.davtyan.sequrity.sequrityapi.dto.register.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PostMapping("/registration")
    public Long registration(@RequestBody UserRequest user) {
        return userService.register(user);
    }

    @PostMapping("/roles")
    public void addRoles(UserRequest userRequest, List<Role> newRoles) {
        roleService.addRoles(userRequest, newRoles);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUsers(@PathVariable("username") String username) {
        User byUserName = userService.findByUserName(username);

        UserResponse userResponse = UserResponse.builder()
            .id(byUserName.getId())
            .username(byUserName.getUserName())
            .firstName(byUserName.getFirstName())
            .lastName(byUserName.getLastName())
            .roles(byUserName.getRoles().stream().map(x -> RoleResponse.builder().name(x.getName()).build()).collect(
                Collectors.toList()))
            .build();

        return ResponseEntity.ok(userResponse);
    }

}
