package com.apcompany.privilege.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apcompany.privilege.dao.UserDao;
import com.apcompany.privilege.pojo.User;
import com.apcompany.privilege.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserByid(int id) {
		return userDao.getUsers(id);
	}

	@Override
	public User getUserByname(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.getUsersByname(name, password);
	}

}
