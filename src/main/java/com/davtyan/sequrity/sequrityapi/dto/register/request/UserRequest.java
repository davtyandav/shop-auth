package com.davtyan.sequrity.sequrityapi.dto.register.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
