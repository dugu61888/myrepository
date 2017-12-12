package com.apcompany.user.service;

import java.util.List;

import com.apcompany.user.pojo.TUser;

public interface TUserServiceI {
	
	 /**
     * 添加用户
     * @param user
     */
    public void addUser(TUser tuser);
    
    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    public TUser getUserById(String userId);
    
    public List<TUser> getAllUser();
   

}
