package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
/*
 DAO란?
 -Data Access Object를 의미하는 애플리케이션의 설계분야 용어
 -Data Access란 데이터베이스와의 CRUD 작업을 전담한다는 의미
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();
	//웹에서도 사용할 수 있도록 중립적인 메서드를 만듬(swing에서 사용하는 메서드를 사용하지 말자!!)
	//재사용성 고려하지 않은 swing 만의 로직 작성
	//insert는 글 한건은 하나의 VO
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;//글 등록 후 그 결과값 보관
		
		con = dbManager.getConnection();
		
		String sql = "insert into notice(author, title, content) values(?, ?, ?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());//작성자
			pstmt.setString(2, notice.getTitle());//제목
			pstmt.setString(3, notice.getContent());//내용
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//모든 레코드 가져오기!!
	public ArrayList<Notice> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Notice> noticeArray = new ArrayList<Notice>();
				
		String sql = "select * from notice order by notice_id desc";
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("Content"));
				notice.setRegdate(rs.getString("regdate"));
				
				noticeArray.add(notice);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);	
		}
		
		return noticeArray;
	}
	
	//게시물 1건 가져오기(상세보기)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		con = dbManager.getConnection();
		
		Notice notice = null;
		
		String sql = "select * from notice where notice_id = ?";
		try {
			pstmt = con.prepareStatement(sql);//쿼리 준비
			pstmt.setInt(1, notice_id);//바인드 변수값 지정
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return notice;
	}
	
	public int delete(int notice_id) {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from notice where notice_id = "+notice_id;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	public int update(Notice notice) {
		int result = 0;
		
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update notice set author = ?, title = ?, content = ? where notice_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}











