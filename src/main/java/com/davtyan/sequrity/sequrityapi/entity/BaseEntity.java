package com.davtyan.sequrity.sequrityapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @CreatedDate
    @Column(name="created")
    private Date created;

    @LastModifiedDate
    @Column(name="updated")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private Status status;
}
