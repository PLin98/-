package com.rin.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rin.sys.domain.LogInfo;
import com.rin.sys.mapper.LogInfoMapper;
import com.rin.sys.service.LogInfoService;
import com.rin.sys.utils.DataGridView;
import com.rin.sys.vo.LogInfoVo;

@Service
public class LogInfoServiceImpl implements LogInfoService {
	
	@Autowired
	private LogInfoMapper logInfoMapper;

	@Override
	public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
		Page<Object> page=PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getLimit());
		List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
		return new DataGridView(page.getTotal(), data);
	}

	@Override
	public void addLogInfo(LogInfoVo logInfoVo) {
		this.logInfoMapper.insertSelective(logInfoVo);
	}

	@Override
	public void deleteLogInfo(Integer logInfoid) {
		this.logInfoMapper.deleteByPrimaryKey(logInfoid);
	}

	@Override
	public void deleteBatchLogInfo(Integer[] ids) {
		for (Integer integer : ids) {
			this.deleteLogInfo(integer);
		}
	}

}
