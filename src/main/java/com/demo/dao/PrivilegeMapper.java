package com.demo.dao;

import org.apache.ibatis.annotations.SelectProvider;

import com.demo.bean.SysPrivilege;

public interface PrivilegeMapper {

	//��selectProviderע���ʱ�� method ��type�����Ǳ����
	//method��Ϊprovuderʵ�����еķ�����
	//type:Ϊ����mehtod��ָ���࣬���Ҹ����б���Ҫ�����޲ι��췽��
	@SelectProvider(method = "selectById", type = PrivilegeProvider.class)
	SysPrivilege selectById (Long id);
}
