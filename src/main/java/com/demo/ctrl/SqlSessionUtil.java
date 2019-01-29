package com.demo.ctrl;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {

	public static SqlSession getSqlSession () throws IOException {
		//通过Reader将配置文件读入
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		
		//FIXME 数据库初始化问题。5.0版本 drive驱动异常  6.0就不会
		//通过SqlSessionFactoryBuilder创建以Reader读取的内容为基础的SqlSessionFactory工厂类
		//在创建SqlSessionFactory工厂类创建的过程中会去解析.xml配置文件中的配置属性及mappers映射的方法信息
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
		
		//然后通过工厂类创建一个SqlSession，就可以使用SqlSession执行sql了
		//SqlSession中的方法是所用mybatis-config.xml映射的方法 如果这些方法中有重名的方法 就会出现方法名引用
		//如果多个xml文件中出现重名的sql方法时可以用全限定名来分别方法是属于哪个文件下的xx.xx.selectAll
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
