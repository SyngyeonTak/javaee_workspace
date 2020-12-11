<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>

<%@ include file="/inc/lib.jsp" %>
<%
	QnADAO dao = new QnADAO();
	int qna_id = Integer.parseInt(request.getParameter("qna_id"));
	
	int result = dao.delete(qna_id);
	
	if(result == 0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제 성공", "/qna/list.jsp"));
	}
	
%>