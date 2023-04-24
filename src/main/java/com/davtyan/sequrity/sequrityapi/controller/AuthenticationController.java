package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.login.request.Credentials;
import com.davtyan.sequrity.sequrityapi.dto.login.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtTokenProvider;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/login")
    public UserResponse login(@RequestBody Credentials credentials) {

        try {
            String userName = credentials.getUserName();
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, credentials.getPassword()));

            User requestUser = userService.findByUserName(userName);
            if (requestUser == null) {
                throw new UsernameNotFoundException("no user named : " + userName);
            }
            UserResponse userResponse = new UserResponse();
            userResponse.setUserName(userName);
            userResponse.setToken(jwtTokenProvider.createToken(userName));
            return userResponse;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid userName or password");
        }

    }
}
