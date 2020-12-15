//���������� �о�鿩, ���丮�� �����ϰ� ������ ���࿡ �ʿ��� SqlSession ��ü�� ��ȯ���ִ� ��ü
package mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	private static MybatisConfigManager instance;
	String resource = "mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;

	//�����ڸ� ���ұ� ������, ���� �� �޼��忡 ���ؼ��� �ν��Ͻ��� �� �� �ֵ��� ��������!!
	public static MybatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MybatisConfigManager();
		}
		return instance;
	}
	
	private MybatisConfigManager() {
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//SqlSession�� ��ȯ�ϴ� �޼���(�������� �����ϰ� ���� �ڰ� ȣ����)
	public SqlSession getSqlSession() {
		SqlSession sqlSession =null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	//SqlSession�� �ݴ� �޼���(SqlSession������ ���� �� �� �޼����� �μ��� �ѱ���)
	public void close(SqlSession sqlSession) {
		sqlSession.close();
	}
}




