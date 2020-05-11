package com.rin.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rin.sys.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 登陆
     */
    User login(User user);
    /**
     * 查询用户
     */
    List<User> queryAllUser(User user);
    /**
     * 保存用户和角色的关系
     * @param userid
     * @param rid
     */
	void insertUserRole(@Param("uid")Integer userid, @Param("rid")Integer rid) ;

	User getOldPwd(@Param("userid")Integer userid,@Param("oldPwd") String oldPwd);

	int changePwd(@Param("userid")Integer userid,@Param("oldPwd") String oldPwd,@Param("confirmPwd") String confirmPwd);

	int changeUser(@Param("userid")Integer userid, @Param("realname")String realname, @Param("usersex")Integer usersex, @Param("phone")String phone, @Param("province")String province);
}