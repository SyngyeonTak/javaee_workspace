<%@page import="board.model.ImageBoardDAO"%>
<%@page import="board.model.ImageBoard"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>

<%!
	String saveDir = "C:/study/ETC/academy/workspace/javaee_workspace/BoardApp/WebContent/data/";
	int maxSize = 2 * 1024 * 1024;
	ImageBoard board = new ImageBoard();
	ImageBoardDAO dao = new ImageBoardDAO();
	String newName = "";
	String oldName = "";
%>

<%
	DiskFileItemFactory itemFactory = new DiskFileItemFactory();
	itemFactory.setRepository(new File(saveDir));
	itemFactory.setSizeThreshold(maxSize);
	itemFactory.setDefaultCharset("utf-8");

	ServletFileUpload upload = new ServletFileUpload(itemFactory);
	
	List<FileItem> items = upload.parseRequest(request);
	
	for(FileItem item : items){
		if(item.isFormField()){
			if(item.getFieldName().equals("board_id")){
				board.setBoard_id(Integer.parseInt(item.getString()));
			}else if(item.getFieldName().equals("author")){
				board.setAuthor(item.getString());
			}else if(item.getFieldName().equals("title")){
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("content")){
				board.setContent(item.getString());
			}else if(item.getFieldName().equals("filename")){
				board.setFilename(item.getString());
				oldName = board.getFilename();
			}
			
		}else{
			if(item.getName().length() > 0){
				
				int lastIdx = item.getName().lastIndexOf(".");
				String ext = item.getName().substring(lastIdx+1, item.getName().length());
				
				newName = System.currentTimeMillis()+"."+ext;
				File file = new File(saveDir+newName);
				item.write(file);
				board.setFilename(newName);
			}
		}
	}
	
	int result = dao.update(board);
	
	if(result == 0){
		out.print(getMsgBack("수정실패"));
	}else{
		if(board.getFilename().equals(newName)){
			File file = new File(saveDir+oldName);
			file.delete();
			out.print(getMsgURL("수정 성공", "/imageboard/list.jsp"));
		}else{
			out.print(getMsgURL("수정 성공", "/imageboard/list.jsp"));			
		}
		
	}
	
%>














