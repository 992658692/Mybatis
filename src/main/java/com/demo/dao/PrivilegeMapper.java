package com.demo.dao;

import org.apache.ibatis.annotations.SelectProvider;

import com.demo.bean.SysPrivilege;

public interface PrivilegeMapper {

	//用selectProvider注解的时候 method 与type参数是必填的
	//method：为provuder实体类中的方法名
	//type:为包含mehtod的指定类，并且该类中必须要包含无参构造方法
	@SelectProvider(method = "selectById", type = PrivilegeProvider.class)
	SysPrivilege selectById (Long id);
}
