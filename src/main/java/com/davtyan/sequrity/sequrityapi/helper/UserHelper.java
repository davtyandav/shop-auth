package com.davtyan.sequrity.sequrityapi.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.entity.Status;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleService roleService;

    public UserHelper(BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

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

    private Role createDefaultRole() {
        return roleService.findByName("user");
    }

}
