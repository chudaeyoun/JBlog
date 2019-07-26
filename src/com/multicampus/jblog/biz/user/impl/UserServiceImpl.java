package com.multicampus.jblog.biz.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multicampus.jblog.biz.user.UserService;
import com.multicampus.jblog.biz.user.UserVO;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	public UserVO getUser(UserVO vo){
		return userDAO.getUser(vo);
	}
}
