package com.davtyan.sequrity.sequrityapi.controller;

import com.davtyan.sequrity.sequrityapi.dto.login.request.RoleRequest;

import com.davtyan.sequrity.sequrityapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping()
    public void addRole(RoleRequest roleRequest) {
        roleService.addRole(roleRequest);
    }

}
