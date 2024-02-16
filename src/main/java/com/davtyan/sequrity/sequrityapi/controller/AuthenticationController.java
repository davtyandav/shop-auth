package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.login.request.Credentials;
import com.davtyan.sequrity.sequrityapi.dto.login.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtTokenProvider;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/login")
    public UserResponse login(@RequestBody Credentials credentials) {
            String userName = credentials.getUserName();
            User requestUser = userService.findByUserName(userName);
            if (requestUser == null) {
                throw new IllegalArgumentException("no user named : " + userName);
            }
            UserResponse userResponse = new UserResponse();
            userResponse.setUserName(userName);
            userResponse.setToken(jwtTokenProvider.createToken(userName));
            return userResponse;
    }
}
