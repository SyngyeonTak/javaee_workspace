<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	String admin_id = "scott";
	String admin_pass = "1234";
	
	/*
	원래는 데이터베이스에서 조회를 해야하지만, 추후 하기로 하고
	일단은 스트링으로 비교 해본다.
	*/
	
	String mid = request.getParameter("mid");
	String password = request.getParameter("password");
	
	if(admin_id.equals(mid) && admin_pass.equals(password)){
		//로그인 성공에 대한 보상 !! 관리자 페이지 보여주기
		//js의 location.href와 동일한 기능의 jsp 기능 이용해보자
		Admin admin = new Admin();
		//관리자 정보를 VO에 담자
		admin.setMid(mid);
		admin.setPassword(password);
		request.setAttribute("admin", admin);
		response.sendRedirect("/admin/index.jsp");//클라이언트로 하여금 지정한 url로 요청을 시도
		
	}else{
		//로그인 실패에 대한 욕!!
		out.print(getMsgBack("접속 실패"));
	}
	

%>





