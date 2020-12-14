<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="board.model.BoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="board.model.Board"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%!
	int maxSize = 2 * 1024 * 1024;
%>
<%
	//multi/form-data방식으로 전송된 파라미터는 업로드 컴포넌트를 통해서 처리해야 한다.
	DiskFileItemFactory itemFactory = new DiskFileItemFactory();
	//자바기반의 웹 어플리케이션은 어떤 OS건 중립적 실행이 보장되어야 하므로, 특정 시스템에 의존하는 경로를 사용해서는 안된다.
	//해결책? 개발자가 경로를 넣으려고 하지말고, 프로그래밍에서 시스템의 경로를 동적으로 얻어와서 이용한다.
	//이때 사용할 jsp의 내장객체가 바로 application 내장객체이다.
	//application 내장객체는, 현재 어플리케이션의 전역적 정보를 가진 객체이므로, 어떤 디렉토리에서 사이트가 실행되는지
	//조차 스스로 알아낼 수 있다.
	String realPath = application.getRealPath("/data");//웹사이트의 root를 기준으로 특정 파일이나, 디렉토리를 명시하면 스스로 현재 
	//웹사이트가 얹혀진 os로부터 풀 경로를 구해준다.
	out.print(realPath);
	
	itemFactory.setRepository(new File(realPath));
	itemFactory.setSizeThreshold(maxSize);
	itemFactory.setDefaultCharset("utf-8");
	
	//아래의 객체가 업로드된 정보를 가지고 있으므로, 파라미터 등도 뽑아 낼 수 있다.
	ServletFileUpload upload = new ServletFileUpload(itemFactory);
	
	 List<FileItem> items = upload.parseRequest(request);
	 
	 Board board = new Board();
	 MybatisBoardDAO dao = new MybatisBoardDAO();
	 
	 boolean flag= false;//업로드가 완료되었는지 여부를 알 수 있는 변수 선언
	 
	 for(FileItem item : items){
		 if(item.isFormField()){//text기반 컴포넌트라면...
			 if(item.getFieldName().equals("title")){
				 board.setTitle(item.getString());
			 }else if (item.getFieldName().equals("writer")){
				 board.setWriter(item.getString());
			 }else if(item.getFieldName().equals("content")){
				 board.setContent(item.getString());				 
			 }
		 }else{//파일이라면
			 
			 String ext = FileManager.getExtend(item.getName());
			 long time = System.currentTimeMillis();
			 String filename = time+"."+ext;
			 board.setFilename(filename);
			 File file = new File(realPath + "/"+filename);
			 item.write(file);
			 
			 //오라클에 insert
			 
			 flag= true;
		 }
	 }
	 
	if(flag){//업로드가 성곤되면 INSERT
		int result = dao.insert(board);
		if(result == 0){
			out.print(getMsgBack("등록 실패"));
		}else{
			out.print(getMsgURL("등록 성공", "/board/list.jsp"));
			
		}
	}
	 
	
%>












