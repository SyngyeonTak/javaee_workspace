<%@page import="java.io.File"%>
<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	

	int board_id = Integer.parseInt(request.getParameter("board_id"));
	String filename = request.getParameter("filename");
	
	//파일삭제(파일을 다룰 수 있는 클래스: java.io.File)
	File file = new File("C:/study/ETC/academy/workspace/javaee_workspace/BoardApp/WebContent/data/"+filename);

	if(file.delete()){
		ImageBoardDAO dao = new ImageBoardDAO();
		dao.delete(board_id);
		out.print(getMsgURL("삭제 성공", "/imageboard/list.jsp"));		
	}else{
		out.print(getMsgBack("삭제 실패"));
	};
	//DB삭제
	


%>