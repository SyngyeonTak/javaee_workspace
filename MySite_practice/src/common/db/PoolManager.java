/*Ŀ�ؼ� Ǯ ����� ���� ���ϰ� ó���ؾ� DAO �鿡�� �������Ѵ�.*/

package common.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
	InitialContext context;//JNDI�˻��� ����ϴ� ��ü
	DataSource ds;//Ŀ�ؼ� Ǯ
	private static PoolManager instance;
	//�� �������ʹ� �ƹ��� new �� �� ����.
	private PoolManager() {
		try {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc/myoracle");//ã�� ���� and Ǯ��ȯ
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static PoolManager getInstance() {
		if(instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}
	
	
	//������ Ŭ������ ����� ���� ���̱⿡ �ν��Ͻ��� ������ ��ȸ�� ���� Ŭ�������� �δ�������!!
	
	//Ŀ�ؼ��� �ʿ��� �ڿ��� Connection�� ��ȯ���ִ� (�뿩) �޼���
	public Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();//�뿩!
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//�ݳ�
	public void release(Connection con, PreparedStatement pstmt) {
		try {
			if(con != null) con.close();//�������� ���� �ƴ϶� �ݳ��̵�
			if(pstmt != null) pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void release(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(con != null) con.close();//�������� ���� �ƴ϶� �ݳ��̵�
			if(pstmt != null) pstmt.close();
			if(rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}






