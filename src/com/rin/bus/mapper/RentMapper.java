package com.rin.bus.mapper;

import java.util.List;

import com.rin.bus.domain.Rent;

public interface RentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);
    
    int updateRentopername(Rent record);
    
    //查询
    public List<Rent> queryAllRent(Rent rent);
    
    public List<Rent> queryRenting(Rent rent);

	List<Rent> queryRent(String identity);
    
}