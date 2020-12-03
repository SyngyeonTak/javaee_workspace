<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ include file="/inc/lib.jsp"%>
<%
/*수정 요청을 처리하는 jsp.... 수정 후 상세보기 페이지로 전환할 것이므로
디자인이 필요없고 오직 db 로직만 있으면 됨...
*/
	request.setCharacterEncoding("utf-8");//전송 파라미터들에 대한 인코딩처리!!

	DBManager dbManager = new DBManager();
	Connection con = dbManager.getConnection();
	PreparedStatement pstmt = null;

	String author = request.getParameter("author");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String notice_id = request.getParameter("notice_id");


	String sql = "update notice set author = '"+author+"', title = '"+title+"', content ='"+content+"' where notice_id = "+notice_id;
	pstmt = con.prepareStatement(sql);
	int result = pstmt.executeUpdate();

	if(result == 0){
			out.print(getMsgBack("수정 실패"));

	}else{
			out.print(getMsgURL("수정성공", "/board/detail_form.jsp?notice_id="+notice_id));
	}

	
%>
