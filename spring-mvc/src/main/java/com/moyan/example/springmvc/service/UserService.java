package com.moyan.example.springmvc.service;


import com.moyan.example.springmvc.model.UserInfo;

public interface UserService {
	
	 UserInfo getUserById(int id);
	      
	 int insert(UserInfo userInfo);
}
