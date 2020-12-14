<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="java.awt.event.ItemEvent"%>
<%@page import="board.model.Board"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	int maxSize = 3 * 1024 * 1024;
	String oldName = "";
	String newName = ""; 
%>

<%
	String realPath = application.getRealPath("/data");
	DiskFileItemFactory itemFactory = new DiskFileItemFactory();
	itemFactory.setRepository(new File(realPath));
	itemFactory.setDefaultCharset("utf-8");
	

	ServletFileUpload upload = new ServletFileUpload(itemFactory);	
	List<FileItem >items = upload.parseRequest(request);
	Board board = new Board();
	for(FileItem item : items){
		if(item.isFormField()){
			if(item.getFieldName().equals("board_id")){
				board.setBoard_id(Integer.parseInt(item.getString()));
			}else if(item.getFieldName().equals("title")){
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("writer")){
				board.setWriter(item.getString());
			}else if(item.getFieldName().equals("content")){
				board.setContent(item.getString());
			}else if(item.getFieldName().equals("filename")){
				board.setFilename(item.getString());
				oldName = board.getFilename();
			}
		}else{
			if(item.getName().length()>0){
				
				newName = System.currentTimeMillis()+"."+FileManager.getExtend(item.getName()); 
				File file = new File(realPath+"/"+newName);
				item.write(file);
				board.setFilename(newName);
			}
		}
	}
	
	MybatisBoardDAO dao = new MybatisBoardDAO();
	int result = dao.update(board);
	
	if(result == 0){
		out.print(getMsgBack("수정 실패"));
	}else{
		if(board.getFilename().equals(newName)){
			File file = new File(realPath+"/"+oldName);
			file.delete();
			out.print(getMsgURL("수정성공", "/board/detail.jsp?board_id="+board.getBoard_id()));
		}else{
			out.print(getMsgURL("수정성공", "/board/list.jsp?board_id="+board.getBoard_id()));			
		}
	}
	
%>

















