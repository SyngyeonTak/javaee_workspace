<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ include file="/inc/lib.jsp"%>
<%
	
	request.setCharacterEncoding("utf-8");

	DBManager dbManager = new DBManager();
	Connection con= dbManager.getConnection();
	PreparedStatement pstmt = null;
	String notice_id = request.getParameter("notice_id");
	String sql = "delete from notice where notice_id = "+notice_id;

	//삭제 후 완료 메시지 보여주고 list.jsp를 요청할 것
	pstmt = con.prepareStatement(sql);
	int result = pstmt.executeUpdate();

	if(result == 0){
		out.print(getMsgBack("삭제 실패"));
		

	}else{
		out.print(getMsgURL("삭제 성공", "/board/list.jsp"));


	}

%>