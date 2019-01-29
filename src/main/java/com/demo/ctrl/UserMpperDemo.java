package com.demo.ctrl;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.demo.bean.SysRole;
import com.demo.bean.SysUser;
import com.demo.dao.RoleMapper;
import com.demo.dao.UserMapper;

public class UserMpperDemo {

	public static void select (SqlSession sqlSession) {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<SysUser> list = userMapper.selectRolesByUserId(1L);
	}
	
	public static void selectByIdList (SqlSession sqlSession) {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1L);
		userMapper.selectByIdList(map);
	}
	
	/**
	 * 一级缓存的测试Demo
	 * 一级缓存是存在与sqlsession的生命周期中，mybatis会将查询的方法与参数通过算法生成缓存的键值
	 * 存入到一个Map中，如果同一个sqlsession执行的方法与参数一致的话，那么对应的生成的map的键值
	 * 也是一样的，这个时候就会返回Map中已经存在缓存对象了。所以在同个sqlsession中执行同样方法的相同参数后
	 * 返回的对象都是同一个。如果想每次执行查询都返回新的对象的话可以在mybatis的select中加入flushCache=true
	 * 属性，该属性可以让每次查询都清空一级缓存(是清空所有的一级缓存,使用的时候要小心点)。
	 * */
	public static void testCacheLevelOne() throws IOException {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		SysUser user =  userMapper.selectById(1L);
		user.setUserName("11");
		SysUser user2 = userMapper.selectById(1L);
		System.out.println(user.getUserName());
		System.out.println(user2.getUserName());
		System.out.println(user.equals(user2));
		sqlSession.close();
		
		sqlSession = SqlSessionUtil.getSqlSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
		user2 = userMapper.selectById(1L);
		System.out.println(user.equals(user2));
	}
	
	/**
	 * 二级缓存的测试Demo
	 * 由于二级缓存与一级缓存的依赖空间不同,是依赖于xml中的namespace的
	 * 而且它的生命周期是归于SqlsessionFactory，不再是Sqlsession的生命周期内
	 * 所以当sqlsession关闭后，二级缓存不会随之消失，而是还存在于SqlsessionFactory的生命周期内
	 * 并且可以通过readOnly 属性设置是否只读 如果非只读(读写)的话那么会通过序列化创建不同的对象，但是还是用的通一个缓存只是对象是新的
	 * 
	 * @throws IOException
	 */
	public static void testCacheLevelTwo () throws IOException {
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		SysUser user =  userMapper.selectById(1L);
		user.setUserName("11");
		SysUser user2 = userMapper.selectById(1L);
		System.out.println(user.getUserName());
		System.out.println(user2.getUserName());
		System.out.println(user.equals(user2));
		sqlSession.close();
		sqlSession = sqlSessionFactory.openSession();
		userMapper = sqlSession.getMapper(UserMapper.class);
		user2 = userMapper.selectById(1L);
		SysUser user3 = userMapper.selectById(1L);
		System.out.println(user.equals(user2));
		System.out.println(user2.equals(user3));
	}
	
	/**
	 * 脏数据的测试Demo
	 * 单表查询的时候并不会产生脏数据，脏数据的产生是因为执行多表关联查询的时候
	 * 由于二级缓存是根据namespace的命名空间来保存的，当user和role关联时，查询保存在usermapper的namespace中时
	 * 但是我执行针对role的update操作并不会去清理user的namespace内的缓存，所以保存在user中的二级缓存与最新的
	 * 数据内容其实是不一致的，这个时候缓存就变成了脏数据了。虽然可以通过cache-ref来制定引用的缓存空间，但是一旦业务
	 * 庞大关联查询比较多的时候，就比较不容易用namespace控制缓存空间了。所以推荐使用redis这种缓存工具来实现sql的缓存
	 * 
	 */
	public static void CacheBadData () throws IOException {
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
		role.setRoleName("脏数据");
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
	
	
	public static void insert (SqlSession sqlSession) {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		SysUser user = new SysUser();
		user.setUserName("test1");
		user.setUserPassword("123456");
		user.setUserEmail("test@qq.com");
		user.setUserInfo("test info");
		user.setCreateTime(new Date());
		user.setHeadImg(new byte[]{1,2,3,4});
		int result = userMapper.insert(user);
		System.out.println(user.getId());
		System.out.println(result);
		//SqlSessionFactory.openSession默认是不自动提交的。所以如果自己不手动commit那数据永远不生效！！
		sqlSession.commit();
	}
	
	public static void update (SqlSession sqlSession) {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		SysUser user = new SysUser();
		user.setId(1l);
		user.setUserName("xpc");
		user.setUserPassword("xpc123");
		user.setUserEmail("xpc@qq.com");
		user.setUserInfo("xpcpc");
		user.setHeadImg(new byte[]{1});
		user.setCreateTime(new Date());
		userMapper.update(user);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public static void delete (SqlSession sqlSession) {
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		SysUser user = new SysUser();
		user.setId(1015L);
		userMapper.deleteById(user);
		sqlSession.commit();
	}
}
