package com.rin.bus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rin.bus.domain.Customer;
import com.rin.bus.mapper.CustomerMapper;
import com.rin.bus.service.CustomerService;
import com.rin.bus.vo.CustomerVo;
import com.rin.sys.constast.SysConstast;
import com.rin.sys.domain.User;
import com.rin.sys.utils.DataGridView;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public DataGridView queryAllCustomer(CustomerVo customerVo) {
		Page<Object> page=PageHelper.startPage(customerVo.getPage(), customerVo.getLimit());
		List<Customer> data = this.customerMapper.queryAllCustomer(customerVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addCustomer(CustomerVo customerVo) {
		// 设置默认密码
		customerVo.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
		this.customerMapper.insertSelective(customerVo);
	}

	@Override
	public void deleteCustomer(String identity) {
		this.customerMapper.deleteByPrimaryKey(identity);
	}

	@Override
	public void deleteBatchCustomer(String[] identitys) {
		for (String identity : identitys) {
			this.deleteCustomer(identity);
		}
	}

	@Override
	public void updateCustomer(CustomerVo customerVo) {
		this.customerMapper.updateByPrimaryKeySelective(customerVo);
	}

	@Override
	public Customer queryCustomerByIdentity(String identity) {
		// TODO Auto-generated method stub
		return this.customerMapper.selectByPrimaryKey(identity);
	}

	@Override
	public List<Customer> queryAllCustomerForList(CustomerVo customerVo) {
		return this.customerMapper.queryAllCustomer(customerVo);
	}

	@Override
	public void resetCustomerPwd(String identity) {
		Customer customer = new Customer();
		customer.setIdentity(identity);
		// 设置默认密码
		customer.setPwd(DigestUtils.md5DigestAsHex(SysConstast.USER_DEFAULT_PWD.getBytes()));
		//更新
		this.customerMapper.updateByPrimaryKeySelective(customer);
		
	}


}
