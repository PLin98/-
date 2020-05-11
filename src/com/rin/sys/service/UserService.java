package com.rin.sys.service;

import com.rin.sys.domain.User;
import com.rin.sys.utils.DataGridView;
import com.rin.sys.vo.UserVo;

/**
 * 用户服务接口
 * @author Rin
 *
 */
public interface UserService {

	User login(UserVo userVo);
	
	/**
	 * 查询所有用户
	 * @param userVo
	 * @return
	 */
	public DataGridView queryAllUser(UserVo userVo);
	
	/**
	 * 添加用户
	 * @param userVo
	 */
	public void addUser(UserVo userVo);

	/**
	 * 修改用户
	 * @param userVo
	 */
	public void updateUser(UserVo userVo);

	/**
	 * 根据id删除用户
	 * @param userid
	 */
	public void deleteUser(Integer userid);
	/**
	 * 批量删除用户
	 * @param userVo
	 */
	public void deleteBatchUser(Integer [] ids);
	
	/**
	 * 重置密码
	 * @param userid
	 */
	public void resetUserPwd(Integer userid);

	/**
	 *  加载用户管理的分配角色的数据
	 * @param userid
	 * @return
	 */
	DataGridView queryUserRole(Integer userid);

	/**
	 * 保存用户和角色的关系
	 * @param userVo
	 */
	void saveUserRole(UserVo userVo);
	
	/**
	 * 修改密码
	 * @param userid
	 * @param repassword 
	 * @param oldPassword 
	 * @return 
	 */
	int changePwd(Integer userid, String oldPassword, String repassword);

	User getOldPwd(Integer userid, String oldpwd);

	int changeUser(Integer userid, String realname, Integer usersex, String phone, String province);
}