package com.davtyan.sequrity.sequrityapi.dto.register.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponse {
    private String name;

    public RoleResponse() {
    }

    public RoleResponse(String name) {
        this.name = name;
    }
}
