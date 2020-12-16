/*
 * mybatis�� config.xml�� xml�ϻ� ���� �������� �ڹ� ���ø����̼ǰ��� ������� �����̴�
 * ���� �ڹ��ڵ忡�� config.xml�� �о�� �Ѵ�.
 * ������ǥ: xml�� �о�鿩�� ���� �������� �������ִ� ��ü�� SqlSession ��� �����̴�.
 * �� Ŭ������ Ư�� new �Ҷ� ���� �ν��Ͻ��� ������ ���̰�, �׷��� �Ǹ� SqlSessionFactory�� �ټ���
 * �޸𸮿� �ö���Ƿ� ,�޸� ������ �� ����, ���� SingleTon �������� ����
 * */
package com.webapp1216.mybatis.config;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	private static MybatisConfigManager instance;//������ 
	String resource = "com/webapp1216/mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	
	public static MybatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MybatisConfigManager();
		}
		
		return instance;
	}
	
	public MybatisConfigManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//SqlSession�� ��ȯ�ϴ� �޼���
	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public void close(SqlSession sqlSession) {
		if(sqlSession != null) {
			sqlSession.close();
		}
	}
}









