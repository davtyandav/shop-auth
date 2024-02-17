package com.davtyan.sequrity.sequrityapi.service;

import com.davtyan.sequrity.sequrityapi.dto.login.request.RoleRequest;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);

    List<Role> getByUserId(long id);

    void addRoles(Long userId, List<Role> newRoles);
    void addRole(RoleRequest roleRequest);
}
