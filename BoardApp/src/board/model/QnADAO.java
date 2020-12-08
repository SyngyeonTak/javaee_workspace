package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class QnADAO {
	DBManager dbManager=new DBManager();
	
	//insert : 원글 등록
	public int insert(QnA qna) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="insert into qna(writer, title ,content) values(?,?,?)";
		try {
			con=dbManager.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			result=pstmt.executeUpdate();//실행
			
			//team을 방금 들어간 레코드에 의해 발생한 pk 값으로 업데이트!!!
			sql="update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt=con.prepareStatement(sql); //쿼리문 1:1 대응하게!!
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	/*	
	1.기존에 내가본글보다 rank가 큰 글의 rank는 모두 1씩 증가되시오!! (공간확보 )
    update  qna  rank=rank+1 where rank > 내본글 rank and 
    team=내본team
	2.빈 공간을 내가 차지!!(답변)
	   insert  qna(~team, rank, depth) values(내본team,내본rank+1,내본depth+1)
	   
	 트랜잭션이란?
	 - 세부업무가 모두 성공해야 전체를 성공으로 간주하는 논리적 업무 수행 단위
	 
	
	 */   
	public int reply(QnA qna) {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt =null;
		
		int result=0;		
		
		try {
			// java에서는 commit을 날릴 필요가 없는데 그 이유는 setAutoCommit이 true로 default 되어 있기 때문이다.
			con.setAutoCommit(false);//자동으로 커밋하지마(SQLplus 처럼 내가 결정할게!!)
			
			//대상이 없는 것을 에러가 아니라 그냥 없는거라 sql문을 넘어간다.(답글이 없을 경우...)
			String sql = "update qna set rank = rank+1 where team = ? and rank > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getTeam());
			pstmt.setInt(2, qna.getRank());
			result = pstmt.executeUpdate();

			
			sql = "insert into qna(writer, title, content, team, rank, depth)";
			sql += " values(?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getTeam());
			pstmt.setInt(5, qna.getRank()+1);//내본 글 다음에 위치할 것이므로+1
			pstmt.setInt(6, qna.getDepth()+1);//내본 글에 대한 답변이므로+1
		
			result = pstmt.executeUpdate();
			
			con.commit();//여기서 커밋 즉, 둘 다 에러 나지 않고 try를 완료하면 모두 성공으로 간주!! 여기서 commit한다.
		} catch (SQLException e) {

			e.printStackTrace();
			try {
				con.rollback();//두 쿼리문 중 에러가 하나라도 발생하면, 차라리 처음부터 없었던 일로 하자...
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				con.setAutoCommit(true);//원상복귀
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbManager.release(con, pstmt);
		}
		
		return result;
	}
	
	//selectAll
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ArrayList<QnA> list = new ArrayList<QnA>();
		
		String sql="select * from qna order by team desc, rank asc";
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				QnA qna = new QnA(); //레코드만큼 vo 생성해야 함!!
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
				
				list.add(qna); //리스트에 추가하기!!
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//select
	public QnA select(int qna_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		QnA qna = null;
		
		String sql="select * from qna where qna_id=?";
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);//바인드 변수 값 지정
			rs=pstmt.executeQuery();
		
			if(rs.next()) {//레코드가 있다면...
				qna = new QnA(); //레코드만큼 vo 생성해야 함!!
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
	
	//update
	public int update(QnA qna) {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt = null;
		
		int result=0;
		
		String sql = "update qna set title=?, content =? where qna_id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setInt(3, qna.getQna_id());
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		return result;
	}
	
	//delete
	public int delete(int qna_id) {
		Connection con = dbManager.getConnection();
		PreparedStatement pstmt = null;
		
		int result=0;
		
		String sql = "delete from qna where qna_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}












