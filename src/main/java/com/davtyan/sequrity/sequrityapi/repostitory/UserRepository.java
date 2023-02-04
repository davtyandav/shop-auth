package com.davtyan.sequrity.sequrityapi.repostitory;

import com.davtyan.sequrity.sequrityapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
