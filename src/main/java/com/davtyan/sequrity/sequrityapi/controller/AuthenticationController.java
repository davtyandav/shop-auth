package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.login.request.Credentials;
import com.davtyan.sequrity.sequrityapi.dto.login.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtTokenProvider;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/login")
    public UserResponse login(@RequestBody Credentials credentials) {
        var userName = credentials.getUserName();
        var requestUser = userService.findByUserName(userName);
        if (requestUser == null) {
            throw new IllegalArgumentException("no user named : " + userName);
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setUserName(userName);
        userResponse.setToken(jwtTokenProvider.createToken(userName));
        return userResponse;
    }
}
