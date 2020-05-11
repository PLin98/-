package com.rin.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rin.sys.domain.User;
import com.rin.sys.service.UserService;
import com.rin.sys.utils.DataGridView;
import com.rin.sys.utils.ResultObj;
import com.rin.sys.utils.WebUtils;
import com.rin.sys.vo.UserVo;

/**
 * 用户管理控制器
 * 
 * @author Rin
 *
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 加载用户列表返回DataGridView
	 */
	@RequestMapping("loadAllUser")
	public DataGridView loadAllUser(UserVo userVo) {
		return this.userService.queryAllUser(userVo);
	}
	
	
	
	
	/**
	 * 添加用户
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(UserVo userVo) {
		try {
			this.userService.addUser(userVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo userVo) {
		try {
			this.userService.updateUser(userVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	
	/**
	 * 删除用户
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(UserVo userVo) {
		try {
			this.userService.deleteUser(userVo.getUserid());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	/**
	 * 批量删除用户
	 */
	@RequestMapping("deleteBatchUser")
	public ResultObj deleteBatchUser(UserVo userVo) {
		try {
			this.userService.deleteBatchUser(userVo.getIds());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	/**
	 * 重置用户密码
	 */
	@RequestMapping("resetUserPwd")
	public ResultObj resetUserPwd(UserVo userVo) {
		try {
			this.userService.resetUserPwd(userVo.getUserid());
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}
	
	/**
	 * 检查旧密码
	 */
	
	@RequestMapping("getOldPwd")
	public Map<String,String> getOldPwd(String oldPwd){
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		Integer userid = user.getUserid();
		oldPwd = DigestUtils.md5Hex(oldPwd); 
		User userres=userService.getOldPwd(userid,oldPwd);
		Map<String,String> data =new HashMap<String,String>();
		 	if(userres!=null){
		 		data.put("msg", "1");
			}else{  
				data.put("msg", "0");
			}  
	    return data;
	  
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("changePwd")
	public Map<String,String> changePwd(String oldPwd,String confirmPwd){
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		Integer userid = user.getUserid();
		oldPwd = DigestUtils.md5Hex(oldPwd);
		confirmPwd = DigestUtils.md5Hex(confirmPwd); 
		int result = userService.changePwd(userid,oldPwd,confirmPwd);
		Map<String,String> data =new HashMap<String,String>();
	 	if(result>0){
	 		data.put("msg", "0"); 
		}else{  
			data.put("msg", "1");
		}  
	 	return data; 
	}
	
	/**
	 * 修改资料
	 */
	@ResponseBody
	@RequestMapping("changeUser")
	public Map<String,String> changeUser(String realname,String sex,String phone,String province){
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		Integer userid = user.getUserid();
		Integer usersex;
		if(sex.equals("男")) {
			usersex=1;
		}else if(sex.equals("女")){
			usersex=0;
		}else {
			usersex=2;
		}
		int result = userService.changeUser(userid,realname,usersex,phone,province);
		Map<String,String> data =new HashMap<String,String>();
	 	if(result>0){
	 		data.put("msg", "0");
		}else{  
			data.put("msg", "1");
		}  
        return data;
	}
	
	/**
	 * 加载用户管理的分配角色的数据
	 */
	@RequestMapping("initUserRole")
	public DataGridView initUserRole(UserVo userVo) {
		return this.userService.queryUserRole(userVo.getUserid());
	}
	
	/**
	 * 保存用户和角色的关系
	 */
	@RequestMapping("saveUserRole")
	public ResultObj saveUserRole(UserVo userVo) {
		try {
			this.userService.saveUserRole(userVo);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
	}
	
	
}
