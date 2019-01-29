package com.demo.ctrl;

import org.apache.ibatis.session.SqlSession;

import com.demo.bean.SysRole;
import com.demo.dao.RoleMapper;

public class RoleMapperDemo {

	public static void selectById (SqlSession sqlSession) {
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole role = roleMapper.selectById(1L);
	}
}
