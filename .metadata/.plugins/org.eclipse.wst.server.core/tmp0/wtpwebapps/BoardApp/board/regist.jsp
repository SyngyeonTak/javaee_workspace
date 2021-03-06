
<%@ page contentType="text/html;charset=utf-8"%>

<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>

<%@page import="board.model.NoticeDAO"%>
<%@page import="board.model.Notice"%>

<%@ include file="/inc/lib.jsp"%>
<%
	NoticeDAO noticeDAO = new NoticeDAO();
	Notice notice = new Notice();
	

	//클라이언트가 전송한 파라미터를 받아서 mysql에 넣을 예정임
	//별도의 디자인 코드는 필요하지 않다.
	out.print("이 페이지에서 클라이언트가 전송한 파라미터들을 데이터베이스에 넣을 예정\n");
	
	//파라미터가 영문이 아닌 경우 깨진다. 따라서 파라미터를 대상으로 한 인코딩을 지정하면 된다.
	//주의!! 파라미터를 받기 전에 미리 세팅해야 한다.
	request.setCharacterEncoding("utf-8");//한국어, 중국어, 아랍어, 전세계 모든 언어가
																//깨지지 않음

	//jsp가 지원하는 내장객체 중, request객체를 이용하여 클라이언트의 전송 파라미터를 받아보자!!
	String author = request.getParameter("author");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	notice.setAuthor(author);
	notice.setTitle(title);
	notice.setContent(content);
	
	int result = noticeDAO.regist(notice);
	
	if(result == 0){
		out.print(getMsgBack("글 등록 실패"));
	}else{
		out.print(getMsgURL("글 등록 설공", "/board/list.jsp"));
		
	}
%>








