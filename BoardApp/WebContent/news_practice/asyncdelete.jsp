<%@page import="board.model.CommentsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%

	int comments_id = Integer.parseInt(request.getParameter("comments_id"));
	CommentsDAO dao = new CommentsDAO();
	int result = dao.delete(comments_id);

	out.print(result);
%>