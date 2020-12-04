<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	request.setCharacterEncoding("utf-8");

	ImageBoard board = new ImageBoard();
	board.setBoard_id(Integer.parseInt(request.getParameter("board_id")));
	board.setAuthor(request.getParameter("author"));
	board.setTitle(request.getParameter("title"));
	board.setContent(request.getParameter("content"));
		
	ImageBoardDAO dao = new ImageBoardDAO();
	
	int result = dao.update(board);
	
	if(result == 0){
		out.print(getMsgBack("수정실패"));
	}else{
		out.print(getMsgURL("수정성공", "/imageboard/detail_form.jsp?board_id="+board.getBoard_id()));		
	}


%>