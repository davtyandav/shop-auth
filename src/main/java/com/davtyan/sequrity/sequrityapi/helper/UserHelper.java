package com.davtyan.sequrity.sequrityapi.helper;

import com.davtyan.sequrity.sequrityapi.dto.register.response.RoleResponse;
import com.davtyan.sequrity.sequrityapi.dto.register.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.entity.Status;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserHelper {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;

    public User createNewUser(UserRequest userRequest) {
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);
        List<Role> collect = new ArrayList<>();
        collect.add(createDefaultRole());
        user.setRoles(collect);
        return user;
    }

    public UserResponse convertToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRoles(user.getRoles().stream().map(this::convertRoleToResponse).collect(Collectors.toList()));
        return userResponse;
    }

    public RoleResponse convertRoleToResponse(Role role) {
        RoleResponse roleResponse=new RoleResponse();
        roleResponse.setName(role.getName());
        return roleResponse;
    }

    private Role createDefaultRole() {
        return roleService.findByName("user");
    }

}
