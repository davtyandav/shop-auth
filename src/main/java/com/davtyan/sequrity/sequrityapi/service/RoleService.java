package com.davtyan.sequrity.sequrityapi.service;

import com.davtyan.sequrity.sequrityapi.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
