package com.moyan.example.springmvc.dao;

import com.moyan.example.springmvc.model.UserInfo;

import java.util.List;


public interface UserInfoMapper {

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

}