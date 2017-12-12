package com.apcompany.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.user.dao.TUserDao;
import com.apcompany.user.pojo.TUser;
import com.apcompany.user.service.TUserServiceI;

@Service("tUserService")
public class TUserServiceImpl implements TUserServiceI {

	@Autowired
	private TUserDao userMapper;

	public void addUser(TUser tuser) {
		userMapper.insert(tuser);

	}

	public TUser getUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}


	public List<TUser> getAllUser() {
		return userMapper.getAllUser();
	}

}
