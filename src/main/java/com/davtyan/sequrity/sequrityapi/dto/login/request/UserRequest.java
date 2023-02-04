package com.davtyan.sequrity.sequrityapi.dto.login.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    private String userName;
    private String password;

}
