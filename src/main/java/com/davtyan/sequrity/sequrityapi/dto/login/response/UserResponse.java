package com.davtyan.sequrity.sequrityapi.dto.login.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String userName;
    private String token;
}
