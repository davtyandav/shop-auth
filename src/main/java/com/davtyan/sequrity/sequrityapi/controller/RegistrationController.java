package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/registration")
    public Long registration(@RequestBody UserRequest user) {
        return userService.register(user).getId();
    }
}
