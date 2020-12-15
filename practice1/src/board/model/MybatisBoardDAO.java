package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.MybatisConfigManager;

public class MybatisBoardDAO {
	MybatisConfigManager configManger = MybatisConfigManager.getInstance();
	
	public int insert(Board board) {
		int result = 0;
		SqlSession sqlSession = configManger.getSqlSession();
		result = sqlSession.insert("mybatis.mappers.BoardMapper.insert", board);
		sqlSession.commit();
		configManger.close(sqlSession);

		return result; 
	}
	
	public Board select(int board_id) {
		Board board = null;
		SqlSession sqlSession = configManger.getSqlSession();
		board = sqlSession.selectOne("mybatis.mappers.BoardMapper.select", board_id);
		sqlSession.commit();
		configManger.close(sqlSession);
		
		return board;
		
	}
	
	public List<Board> selectAll(){
		List<Board> list = null;
		SqlSession sqlSession = configManger.getSqlSession();
		list = sqlSession.selectList("mybatis.mappers.BoardMapper.selectAll");
		sqlSession.commit();
		configManger.close(sqlSession);
		
		return list;
		
	}
	
	public int update(Board board) {
		SqlSession sqlSession = configManger.getSqlSession();
		int result = 0;
		result = sqlSession.update("mybatis.mappers.BoardMapper.update", board);
		sqlSession.commit();
		configManger.close(sqlSession);
		
		return result;
		
	}
	
	public int delete(int board_id) {
		SqlSession sqlSession = configManger.getSqlSession();
		int result = 0;
		result =sqlSession.delete("mybatis.mappers.BoardMapper.delete", board_id);
		sqlSession.commit();
		configManger.close(sqlSession);
		
		return result;
		
	}
	
}










