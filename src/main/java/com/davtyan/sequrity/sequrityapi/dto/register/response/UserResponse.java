package com.davtyan.sequrity.sequrityapi.dto.register.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleResponse> roles;
}
