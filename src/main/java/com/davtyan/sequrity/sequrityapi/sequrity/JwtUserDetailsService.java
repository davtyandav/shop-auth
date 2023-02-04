package com.davtyan.sequrity.sequrityapi.sequrity;

import com.davtyan.sequrity.sequrityapi.entity.User;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtUser;
import com.davtyan.sequrity.sequrityapi.sequrity.jwt.JwtUserFactory;
import com.davtyan.sequrity.sequrityapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("user whit userNme " + username + " not found");
        }

        return JwtUserFactory.create(user);
    }
}
