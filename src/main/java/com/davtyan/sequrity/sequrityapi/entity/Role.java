package com.davtyan.sequrity.sequrityapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role extends BaseEntity{

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy ="roles" ,fetch = FetchType.LAZY)
    private List<User> users;
}
