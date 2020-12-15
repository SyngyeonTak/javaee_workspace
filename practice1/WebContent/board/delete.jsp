<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	int board_id = Integer.parseInt(request.getParameter("board_id"));
	String filename = request.getParameter("filename");
	MybatisBoardDAO dao = new MybatisBoardDAO();
	
	//이미지 삭제 하고 db 레코드 지우기
	String path = application.getRealPath("/data");
	if(FileManager.deleteFile(path+"/"+filename)){
		int result = dao.delete(board_id);
		if(result == 0){
			out.print(getMsgBack("삭제 실패"));		
		}else{
			out.print(getMsgURL("삭제 성공", "list.jsp"));
		}
	}else{
		out.print(getMsgBack("파일을 삭제할 수 없습니다."));		
	}
	
	
%>