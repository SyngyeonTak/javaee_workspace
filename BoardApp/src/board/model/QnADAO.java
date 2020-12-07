package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class QnADAO {
	DBManager dbManager = new DBManager();
	// insert: 원글 등록
	
	public int insert(QnA qna) {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt = null;
		
		int result = 0;

		String sql = "insert into qna(writer, title, content) values(?, ? ,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			
			result = pstmt.executeUpdate();//실행
			
			//team을 방금 들어간 레코드에 의해 발생한 pk값으로 업데이트!!!
			sql = "update qna set team = (select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt = con.prepareStatement(sql);//쿼리문 1:1 대응하겐
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		
		return result;

	}

	// 답변글
	/*
	1) 기존에 내가 본 글보다 rank가 큰 글들은 1씩 증가되도록... update qna set rank = rank+1 where
	  	rank > 내본글 랭크 and team = 내가본글의 팀;
	  
	2) 빈공간 할당 insert qna(team, rank, depth) values(내본글의 팀, 내본글의 rank+1, 내본글의
	  	depth+1);
	 */
	public int reply() {
		
		int result = 0;
		
		
		return result;
	}

	// select
	public QnA select(int qna_id) {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		QnA qna = null;
		String sql = "select * from qna where qna_id = ?";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				qna = new QnA();
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return qna;
	}

	// selectAll
	public List<QnA> selectAll() {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		ArrayList<QnA> list = new ArrayList<QnA>(); 
		String sql = "select * from qna order by team desc, rank asc";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnA qna = new QnA();
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
				
				list.add(qna);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		
		return list;
	}

	// update
	public int update() {
		int result = 0;

		return result;
	}

	// delete
	public int delete() {
		int result = 0;

		return result;
	}

}
