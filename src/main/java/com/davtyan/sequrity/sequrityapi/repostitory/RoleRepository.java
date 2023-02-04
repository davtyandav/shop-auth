package com.davtyan.sequrity.sequrityapi.repostitory;

import com.davtyan.sequrity.sequrityapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
