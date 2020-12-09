<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	int news_id = Integer.parseInt(request.getParameter("news_id"));
	NewsDAO dao = new NewsDAO();
	int result = dao.delete(news_id);
	
	if(result == 0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제 성공", "list.jsp"));
	}

%>