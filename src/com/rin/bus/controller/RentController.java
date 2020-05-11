package com.rin.bus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rin.bus.domain.Customer;
import com.rin.bus.domain.Rent;
import com.rin.bus.service.CustomerService;
import com.rin.bus.service.RentService;
import com.rin.bus.vo.RentVo;
import com.rin.sys.constast.SysConstast;
import com.rin.sys.domain.User;
import com.rin.sys.utils.DataGridView;
import com.rin.sys.utils.RandomUtils;
import com.rin.sys.utils.ResultObj;
import com.rin.sys.utils.WebUtils;

/**
 * 汽车出租管理的控制器
 * @author Rin
 *
 */
@RestController
@RequestMapping("rent")
public class RentController {

	@Autowired
	private RentService rentService;
	
	@Autowired
	private CustomerService customerService;
	
	
	/**
	 * 检查身份证号是否存在
	 */
	@RequestMapping("checkCustomerExist")
	public ResultObj checkCustomerExist(RentVo rentVo) {
		Customer customer=customerService.queryCustomerByIdentity(rentVo.getIdentity());
		if(null!=customer) {
			return ResultObj.STATUS_TRUE;
		}else {
			return ResultObj.STATUS_FALSE;
		}
	}
	
	/**
	 * 初始化添加出租单的表单数据
	 */
	@RequestMapping("initRentFrom")
	public RentVo initRentFrom(RentVo rentVo) {
		//生成出租单号
		rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
		//设置起租时间
		rentVo.setBegindate(new Date());
		
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		//设置操作员
		rentVo.setOpername(user.getRealname());
		return rentVo;
	}
	
	/**
	 * 保存出租单信息
	 */
	@RequestMapping("saveRent")
	public ResultObj saveRent(RentVo rentVo) {
		
			//设置创建时间
			rentVo.setCreatetime(new Date());
			//设置归还状态
			rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
			Customer customer=customerService.queryCustomerByIdentity(rentVo.getIdentity());
			List<Rent> rentlist=new ArrayList<Rent>();
			rentlist=this.rentService.queryRent(customer.getIdentity());
			int i=0;
			for (Rent rent : rentlist) {
				if(rent.getRentflag()==0 ||rent.getRentflag()==2)
					i++;
			}
			if(i==0) {
				this.rentService.addRent(rentVo);//添加订单
				return ResultObj.ADD_SUCCESS;
			}else {
				return ResultObj.ADD_ERROR_ORDER;
			}
	}
	
	
	/***************出租单管理*****************/
	
	/**
	 * 查询
	 */
	@RequestMapping("loadAllRent")
	public DataGridView loadAllRent(RentVo rentVo) {
		return this.rentService.queryAllRent(rentVo);
	}
	
	@RequestMapping("loadRenting")
	public DataGridView queryRenting(RentVo rentVo) {
		return this.rentService.queryRenting(rentVo);
	}
	
	
	@RequestMapping("updateRentopername")
	public ResultObj updateRentopername(RentVo rentVo) {
		try {
			//保存
			User user=(User) WebUtils.getHttpSession().getAttribute("user");
			//设置操作
			rentVo.setOpername(user.getRealname());
			rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
			this.rentService.updateRentopername(rentVo);
			
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	
	/**
	 * 修改出租单信息
	 */
	@RequestMapping("updateRent")
	public ResultObj updateRent(RentVo rentVo) {
		try {
			//保存
			this.rentService.updateRent(rentVo);
			
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}
	

	/**
	 * 删除租单信息
	 */
	@RequestMapping("deleteRent")
	public ResultObj deleteRent(RentVo rentVo) {
		try {
			this.rentService.deleteRent(rentVo.getRentid());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}
	
	
	
	
}
