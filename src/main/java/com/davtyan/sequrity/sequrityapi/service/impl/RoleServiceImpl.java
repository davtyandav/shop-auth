package com.davtyan.sequrity.sequrityapi.service.impl;

import com.davtyan.sequrity.sequrityapi.dto.login.request.RoleRequest;
import com.davtyan.sequrity.sequrityapi.dto.role.UserRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.repostitory.RoleRepository;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getByUserId(long id) {
        return null;
    }

    @Override
    public void addRoles(UserRequest userRequest, List<Role> newRoles) {
        List<Role> oldRoles = getByUserId(userRequest.getId());

        List<Role> deleted = oldRoles.stream().filter(oldRole -> !newRoles.contains(oldRole)).collect(Collectors.toList());
        List<Role> added = newRoles.stream().filter(newRole -> !oldRoles.contains(newRole)).collect(Collectors.toList());


    }

    @Override
    public void addRole(RoleRequest roleRequest) {

        Role role = new Role();
        role.setName(roleRequest.getName());
        roleRepository.save(role);
    }


}

