package com.webapp1216.board.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.webapp1216.mybatis.config.MybatisConfigManager;

public class NoticeDAO {
	//CRUD
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();//singleTon °´Ã¼ ¾ò±â
	
	public int insert(Notice notice) {
		int result = 0;
		SqlSession sqlSession= configManager.getSqlSession();
		result = sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();
		configManager.close(sqlSession);
		
		return result;
	}
	
	public Notice select(int notice_id) {
		Notice notice =null;
		SqlSession sqlSession= configManager.getSqlSession();
		notice = sqlSession.selectOne("Notice.select", notice_id);
		configManager.close(sqlSession);
		
		return notice;
	}
	
	public List<Notice> selectAll(){
		List<Notice> list = null;
		SqlSession sqlSession = configManager.getSqlSession();
		list = sqlSession.selectList("Notice.selectAll");
		configManager.close(sqlSession);
		return list;
		
	}
	
	public int delete(int notice_id) {
		int result = 0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.delete("Notice.delete", notice_id);
		sqlSession.commit();
		configManager.close(sqlSession);
		
		return result;
	}
	
	public int update(Notice notice) {
		int result =0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.update("Notice.update", notice);
		sqlSession.commit();
		configManager.close(sqlSession);

		return result;
	}
	
}





