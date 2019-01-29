package com.demo.ctrl;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.demo.bean.SysUser;
import com.demo.dao.UserMapper;

/**
 * 接口的动态代理简单实现
 * @author xin
 *
 * @param <T>
 */
public class MapperProxy<T> implements InvocationHandler{
	
	private Class<T> mapperInterface;
	private SqlSession sqlSession;
	
	public MapperProxy(Class<T> mapperInterface, SqlSession sqlSession) {
		this.mapperInterface  = mapperInterface;
		this.sqlSession = sqlSession;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//针对不同的sql类型，需要调用的sqlSession的方法也不相同，这里只是调用最简单的无参查询
		List<T> list = sqlSession.selectList(mapperInterface.getCanonicalName() + "." +method.getName());
		return list;
	}

	public static void main(String[] args) throws IOException {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		MapperProxy<UserMapper> mapper = new MapperProxy<>(UserMapper.class, sqlSession);
		UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				new Class[]{UserMapper.class}, mapper);
		List<SysUser> list = userMapper.selectAll();
	}
}
