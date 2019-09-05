package com.moyan.example.springmvc.service.Impl;


import com.moyan.example.springmvc.dao.UserInfoMapper;
import com.moyan.example.springmvc.model.UserInfo;
import com.moyan.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired(required=false)
	private UserInfoMapper userInfoMapper;

	public UserInfo getUserById(int id) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPrimaryKey(id);
	}

	public int insert(UserInfo userInfo) {
		// TODO Auto-generated method stub
		int result = userInfoMapper.insert(userInfo);
		
		return result;
	}
	
	
}
