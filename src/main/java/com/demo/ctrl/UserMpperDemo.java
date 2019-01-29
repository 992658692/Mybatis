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
	 * һ������Ĳ���Demo
	 * һ�������Ǵ�����sqlsession�����������У�mybatis�Ὣ��ѯ�ķ��������ͨ���㷨���ɻ���ļ�ֵ
	 * ���뵽һ��Map�У����ͬһ��sqlsessionִ�еķ��������һ�µĻ�����ô��Ӧ�����ɵ�map�ļ�ֵ
	 * Ҳ��һ���ģ����ʱ��ͻ᷵��Map���Ѿ����ڻ�������ˡ�������ͬ��sqlsession��ִ��ͬ����������ͬ������
	 * ���صĶ�����ͬһ���������ÿ��ִ�в�ѯ�������µĶ���Ļ�������mybatis��select�м���flushCache=true
	 * ���ԣ������Կ�����ÿ�β�ѯ�����һ������(��������е�һ������,ʹ�õ�ʱ��ҪС�ĵ�)��
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
	 * ��������Ĳ���Demo
	 * ���ڶ���������һ������������ռ䲻ͬ,��������xml�е�namespace��
	 * �����������������ǹ���SqlsessionFactory��������Sqlsession������������
	 * ���Ե�sqlsession�رպ󣬶������治����֮��ʧ�����ǻ�������SqlsessionFactory������������
	 * ���ҿ���ͨ��readOnly ���������Ƿ�ֻ�� �����ֻ��(��д)�Ļ���ô��ͨ�����л�������ͬ�Ķ��󣬵��ǻ����õ�ͨһ������ֻ�Ƕ������µ�
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
	 * �����ݵĲ���Demo
	 * �����ѯ��ʱ�򲢲�����������ݣ������ݵĲ�������Ϊִ�ж�������ѯ��ʱ��
	 * ���ڶ��������Ǹ���namespace�������ռ�������ģ���user��role����ʱ����ѯ������usermapper��namespace��ʱ
	 * ������ִ�����role��update����������ȥ����user��namespace�ڵĻ��棬���Ա�����user�еĶ������������µ�
	 * ����������ʵ�ǲ�һ�µģ����ʱ�򻺴�ͱ�����������ˡ���Ȼ����ͨ��cache-ref���ƶ����õĻ���ռ䣬����һ��ҵ��
	 * �Ӵ������ѯ�Ƚ϶��ʱ�򣬾ͱȽϲ�������namespace���ƻ���ռ��ˡ������Ƽ�ʹ��redis���ֻ��湤����ʵ��sql�Ļ���
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
		role.setRoleName("������");
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
		//SqlSessionFactory.openSessionĬ���ǲ��Զ��ύ�ġ���������Լ����ֶ�commit��������Զ����Ч����
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
