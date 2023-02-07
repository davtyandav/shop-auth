package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return userService.register(user).getId();
    }

    @PostMapping("/roles")
    public void addRoles(com.davtyan.sequrity.sequrityapi.dto.role.UserRequest userRequest, List<Role> newRoles) {
        roleService.addRoles(userRequest, newRoles);
    }

}
