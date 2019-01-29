package com.demo.ctrl;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.bean.SysRole;
import com.demo.bean.SysUser;
import com.demo.dao.RoleMapper;
import com.demo.dao.UserMapper;

@Controller
public class Demo extends Tesyt{
	
	public Demo() {
		super(new SysUser());
	}

	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("test")
	public void test () {
		userMapper.selectById(1L);
	}
	
	public void ttt () {
		this.user.setUserName("1111");
	}

	public static void main(String[] args) throws IOException {
		Demo d = new Demo();
		d.user.setCreateTime(new Date());
		
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<SysUser> user =  userMapper.selectRolesByUserId(1001L);
		sqlSession.close();
		
		sqlSession = sqlSessionFactory.openSession();
		RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
		SysRole role = roleMapper.selectById(1L);
		role.setRoleName("ิเสýพÝ");
		int ii = roleMapper.updateRoleById(role);
		sqlSession.commit();
		sqlSession.close();
		
		sqlSession = sqlSessionFactory.openSession();
		RoleMapper roleMap = sqlSession.getMapper(RoleMapper.class);
		UserMapper userMap = sqlSession.getMapper(UserMapper.class);
		List<SysUser> u = userMap.selectRolesByUserId(1001l);
		SysRole r = roleMap.selectById(1L);
		sqlSession.close();
		
	}
}
