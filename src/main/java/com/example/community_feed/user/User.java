package com.example.community_feed.user;

import com.example.community_feed.commons.constant.Role;
import com.example.community_feed.commons.constant.UserState;
import com.example.community_feed.commons.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@DynamicInsert
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column(length = 100)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "varchar(50) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private UserState state;


}
