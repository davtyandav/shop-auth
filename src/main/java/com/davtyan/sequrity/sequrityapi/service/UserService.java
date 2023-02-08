package com.davtyan.sequrity.sequrityapi.service;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.dto.register.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.User;

import java.util.List;

public interface UserService {

    long register(UserRequest user);

    List<User> getAll();

    User findByUserName(String userName);

    User findById(Long id);

    void delete(Long id);
}
