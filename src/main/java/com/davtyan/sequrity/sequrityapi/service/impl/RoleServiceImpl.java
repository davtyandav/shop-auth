package com.davtyan.sequrity.sequrityapi.service.impl;

import com.davtyan.sequrity.sequrityapi.dto.login.request.RoleRequest;
import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.repostitory.RoleRepository;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public List<Role> getByUserId(long id) {
        return null;
    }

    @Override
    public void addRoles(Long userId, List<Role> newRoles) {
        List<Role> oldRoles = getByUserId(userId);

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

