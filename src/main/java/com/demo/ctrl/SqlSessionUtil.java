package com.demo.ctrl;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {

	public static SqlSession getSqlSession () throws IOException {
		//ͨ��Reader�������ļ�����
		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		
		//FIXME ���ݿ��ʼ�����⡣5.0�汾 drive�����쳣  6.0�Ͳ���
		//ͨ��SqlSessionFactoryBuilder������Reader��ȡ������Ϊ������SqlSessionFactory������
		//�ڴ���SqlSessionFactory�����ഴ���Ĺ����л�ȥ����.xml�����ļ��е��������Լ�mappersӳ��ķ�����Ϣ
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		reader.close();
		
		//Ȼ��ͨ�������ഴ��һ��SqlSession���Ϳ���ʹ��SqlSessionִ��sql��
		//SqlSession�еķ���������mybatis-config.xmlӳ��ķ��� �����Щ�������������ķ��� �ͻ���ַ���������
		//������xml�ļ��г���������sql����ʱ������ȫ�޶������ֱ𷽷��������ĸ��ļ��µ�xx.xx.selectAll
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
