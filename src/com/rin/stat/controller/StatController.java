package com.rin.stat.controller;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rin.bus.domain.Customer;
import com.rin.bus.domain.Rent;
import com.rin.bus.service.CustomerService;
import com.rin.bus.service.RentService;
import com.rin.bus.vo.CustomerVo;
import com.rin.stat.domain.BaseEntity;
import com.rin.stat.service.StatService;
import com.rin.stat.utils.ExprotCustomerUtils;
import com.rin.stat.utils.ExprotRentUtils;

/**
 * 统计分析
 * @author Rin
 *
 */
@RequestMapping("stat")
@Controller
public class StatController {
	
	@Autowired
	private StatService statService;
	
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RentService rentService;
	
	/**
	 * 跳转到客户地区统计页面
	 */
	@RequestMapping("toCustomerAreaStat")
	public String toCustomerAreaStat() {
		return "stat/customerAreaStat";
	}
	/**
	 * 加载客户地区数据
	 */
	@RequestMapping("loadCustomerAreaStatJosn")
	@ResponseBody
	public List<BaseEntity> loadCustomerAreaStatJosn(){
		return this.statService.loadCustomerAreaStatList();
	}
	
	
	/**
	 * 跳转到业务员年度统计页面
	 */
	@RequestMapping("toOpernameYearGradeStat")
	public String toOpernameYearGradeStat() {
		return "stat/opernameYearGradeStat";
	}
	
	/**
	 * 加载业务员年度统计数据
	 */
	@RequestMapping("loadOpernameYearGradeStat")
	@ResponseBody
	public Map<String,Object> opernameYearGradeStat(String year){
		List<BaseEntity> entities=this.statService.loadOpernameYearGradeStatList(year);
		Map<String,Object> map=new HashMap<String, Object>();
		List<String> names=new ArrayList<String>();
		List<Double> values=new ArrayList<Double>();
		for (BaseEntity baseEntity : entities) {
			names.add(baseEntity.getName());
			values.add(baseEntity.getValue());
		}
		map.put("name", names);
		map.put("value", values);
		return map;
	}
	
	
	/**
	 * 跳转到公司年度统计页面
	 */
	@RequestMapping("toCompanyYearGradeStat")
	public String toCompanyYearGradeStat() {
		return "stat/companyYearGradeStat";
	}
	
	/**
	 * 加载业务员年度统计数据
	 */
	@RequestMapping("loadCompanyYearGradeStat")
	@ResponseBody
	public List<Double> loadCompanyYearGradeStat(String year){
		List<Double> entities=this.statService.loadCompanyYearGradeStatList(year);
		for (int i = 0; i < entities.size(); i++) {
			if(null==entities.get(i)) {
				entities.set(i, 0.0);
			}
		}
		return entities;
	}
	
	
	/**
	 * 导出客户数据
	 */
	@RequestMapping("exportCustomer")
	public ResponseEntity<Object> exportCustomer(CustomerVo customerVo,HttpServletResponse response) {
		List<Customer> customers=customerService.queryAllCustomerForList(customerVo);
		String fileName="客户数据.xls";
		String sheetName="客户数据";
		ByteArrayOutputStream bos=ExprotCustomerUtils.exportCustomer(customers,sheetName);
		
		try {
			fileName=URLEncoder.encode(fileName,"UTF-8");//处理文件名乱码
			//创建封装响应头信息的对象
			HttpHeaders header=new HttpHeaders();
			//封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//设置下载的文件的名称
			header.setContentDispositionFormData("attachment", fileName);
			return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 导出出租单数据
	 */
	@RequestMapping("exportRent")
	public ResponseEntity<Object> exportRent(String rentid) {
		//根据出租单号查询出租单信息
		Rent rent=rentService.queryRentByRentId(rentid);
		//根据身份证号查询客户信息
		Customer customer=customerService.queryCustomerByIdentity(rent.getIdentity());
		
		
		String fileName=customer.getCustname()+"-的出租单.xls";
		String sheetName=customer.getCustname()+"出租单";
		java.io.ByteArrayOutputStream bos=ExprotRentUtils.exportRent(rent,customer,sheetName);
		
		try {
			fileName=URLEncoder.encode(fileName,"UTF-8");//处理文件名乱码
			//创建封装响应头信息的对象
			HttpHeaders header=new HttpHeaders();
			//封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			//设置下载的文件的名称
			header.setContentDispositionFormData("attachment", fileName);
			return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
