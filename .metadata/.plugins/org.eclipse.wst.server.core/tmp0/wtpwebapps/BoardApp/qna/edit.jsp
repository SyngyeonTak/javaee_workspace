<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ include file="/inc/lib.jsp" %>
<%@ page contentType="text/html;charset=utf-8"%>

<%
	request.setCharacterEncoding("utf-8");

	QnADAO dao = new QnADAO();
	int qna_id = Integer.parseInt(request.getParameter("qna_id"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	QnA qna = new QnA();
	qna.setQna_id(qna_id);
	qna.setTitle(title);
	qna.setContent(content);
	
	int result = dao.update(qna);
	
	if (result == 0) {
		out.print(getMsgBack("수정 실패"));
	} else {
		out.print(getMsgURL("수정 성공", "/qna/detail_form.jsp?qna_id="+qna_id));
}
%>