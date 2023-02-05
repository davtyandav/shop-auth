package com.davtyan.sequrity.sequrityapi.dto.login.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {

    private String userName;
    private String password;

}
