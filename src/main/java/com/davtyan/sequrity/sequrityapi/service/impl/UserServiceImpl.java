package com.davtyan.sequrity.sequrityapi.service.impl;

import com.davtyan.sequrity.sequrityapi.dto.register.request.UserRequest;
import com.davtyan.sequrity.sequrityapi.dto.register.response.UserResponse;
import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.helper.UserHelper;
import com.davtyan.sequrity.sequrityapi.repostitory.UserRepository;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    @Override
    public UserResponse register(UserRequest userRequest) {
        String userName = userRequest.getUserName();
        User requestUser = userRepository.findByUserName(userName);
        if (requestUser == null) {
            User newUser = userHelper.createNewUser(userRequest);
            User saveUser = userRepository.save(newUser);
            return userHelper.convertToResponse(saveUser);
        }
        throw new BadCredentialsException("There is already a user named :" + userName);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
