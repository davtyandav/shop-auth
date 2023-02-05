package com.davtyan.sequrity.sequrityapi.service;

import com.davtyan.sequrity.sequrityapi.dto.role.UserRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(String name);

    List<Role> getByUserId(long id);

    void addRoles(UserRequest userRequest, List<Role> newRoles);
}
