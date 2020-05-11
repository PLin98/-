package com.rin.bus.mapper;

import java.util.List;

import com.rin.bus.domain.Customer;

public interface CustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    List<Customer> queryAllCustomer(Customer customer);
}