package com.apcompany.privilege.service;

import com.apcompany.privilege.pojo.User;

public interface AccountService {

	public User getUserByid(int id);

	public User getUserByname(String name, String password);

}
