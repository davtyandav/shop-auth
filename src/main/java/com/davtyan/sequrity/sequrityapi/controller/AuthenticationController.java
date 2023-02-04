package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.login.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.dto.register.response.RoleResponse;
import com.davtyan.sequrity.sequrityapi.dto.register.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtTokenProvider;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtUser;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public String login(@RequestBody UserRequest userRequest) {

        try {
            String requestUserName = userRequest.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestUserName, userRequest.getPassword()));

            User requestUser = userService.findByUserName(requestUserName);
            if (requestUser == null) {
                throw new UsernameNotFoundException("no user named : " + requestUserName);
            }

            return jwtTokenProvider.createToken(requestUserName);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid userName or password");
        }

    }

    @GetMapping("/api/auth/{authorization}")
    public ResponseEntity<Object> auth(@PathVariable String authorization) {
        String token = authorization.substring(7);
        try {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            UserResponse userResponse = getUserResponse(jwtUser);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private UserResponse getUserResponse(JwtUser jwtUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(jwtUser.getId());
        userResponse.setUserName(jwtUser.getUsername());
        userResponse.setFirstName(jwtUser.getFirsName());
        userResponse.setLastName(jwtUser.getLastName());
        userResponse.setEmail(jwtUser.getEmail());
        List<RoleResponse> roles = jwtUser.getAuthorities().stream()
                .map(grantedAuthority -> new RoleResponse(grantedAuthority.getAuthority())).collect(Collectors.toList());
        userResponse.setRoles(roles);
        return userResponse;
    }
}
