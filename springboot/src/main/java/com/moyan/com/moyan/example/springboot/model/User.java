package com.moyan.com.moyan.example.springboot.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class User {
    private String id;
    private String userName;
    private String password;
    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}