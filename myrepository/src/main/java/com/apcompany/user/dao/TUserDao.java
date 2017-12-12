package com.apcompany.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.apcompany.user.pojo.TUser;
import com.apcompany.user.pojo.TUserExample;
public interface TUserDao {
    int countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(String userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByExample(@Param("record") TUser record, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
    
    /**获取所有用户信息
     * @return List<User>
     */
    List<TUser> getAllUser();
}