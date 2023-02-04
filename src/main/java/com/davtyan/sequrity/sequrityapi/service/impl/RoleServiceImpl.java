package com.davtyan.sequrity.sequrityapi.service.impl;

import com.davtyan.sequrity.sequrityapi.entity.Role;
import com.davtyan.sequrity.sequrityapi.repostitory.RoleRepository;
import com.davtyan.sequrity.sequrityapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
