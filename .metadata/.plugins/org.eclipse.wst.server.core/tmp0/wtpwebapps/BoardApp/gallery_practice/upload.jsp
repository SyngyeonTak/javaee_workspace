<%@page import="common.file.FileManager"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.DefaultFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%

	/*
		String saveDirectory = "C:/study/ETC/academy/workspace/javaee_workspace/BoardApp/WebContent/data";
		int maxSize = 2 * 1024 * 1024;
		String encoding = "utf-8";
	
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxSize, encoding);
		String msg = multi.getParameter("msg");
		String ori = multi.getOriginalFileName("photo");
	
		out.print(msg+"\n");
		out.print(ori);
	*/
	String saveDir = "C:/study/ETC/academy/workspace/javaee_workspace/BoardApp/WebContent/data";
	int maxSize = 2* 1024 * 1024;
	
	DefaultFileItemFactory itemFactory = new DefaultFileItemFactory();
	itemFactory.setRepository(new File(saveDir));
	itemFactory.setSizeThreshold(maxSize);
	
	ServletFileUpload upload = new ServletFileUpload(itemFactory);//설정정보를 생성자의 인수로 전달
	
	request.setCharacterEncoding("utf-8");
	
	List<FileItem> items= upload.parseRequest(request);
	
	for(FileItem item : items){
		if(!item.isFormField()){
			
			String ext = FileManager.getExtend(item.getName());
			String filename = System.currentTimeMillis()+"."+ext;
			File file = new File(saveDir+"/"+filename);
			item.write(file);
		}
	}
	out.print("성공");
%>













