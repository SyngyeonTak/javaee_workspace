<%@page import="java.io.File"%>
<%@page import="common.file.FileManager"%>
<%@page import="java.io.IOException"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%
	/*
	클라이언트가 전송한 제목 텍스트 및 바이너리 파일을 서버의 특정 디렉토리에 저장해보자
	=업로드라고 한다.
	*/
	request.setCharacterEncoding("utf-8");//파라미터에 한글 깨지지 않도록 인코딩 처리
	//String msg = request.getParameter("msg");//String 메시지 받기
	
	//이미지는 글씨가 아닌 바이너리 파일이므로, request.getParameter로는 받을 수 없다!!
	//따라서 IO 네트워크등의 처리를 해야 하는데, 이 자체만으로도 하나의 개발 프로젝트일 것이다.
	//해결책?? 누군가가 만든 라이브러리를 이용해서 개발시간을 단축하자!!
	//현재 우리가 선택한 라이브러리는 cos.jar라는 Oreilly라는 출판사에서 제작한 제작한 컴포넌트이다!!
	
	String saveDirectory = "C:/study/ETC/academy/workspace/javaee_workspace/BoardApp/WebContent/data"; //하드디스크의 물리적 full 경로를 명시해야 한다.
	int maxSize = 2 * 1024 * 1024; //2M byte
	String encoding = "utf-8";
	//FileRenamePolicy policy: 업로드시, 동일한 파일을 업로드 했을때?? 자동으로 이름을 부여한다..
	//예) p.jpg		1p.jpg	2.jpg...(파일명은 개발자가 주도하여 명명하므로, pilicy를 굳이 이용할 필요없다..)
	
	

	try{
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxSize, encoding);//업로드 발생!!
		String msg = multi.getParameter("msg");
		out.print("님이 전송한 메시지는 "+ msg);
		//업로드가 완료된 후, 즉 서버의 저장소에 파일이 존재하게 된 후 해야할 일!!
		//파일명을 개발자가 정한 규칙으로 정하자!!... 현재시간의 밀리세컨드까지 구해보자!!
		long time = System.currentTimeMillis();
		out.print(time);
		//구한 시간에 확장자를 붙이면 최종적으로 파일명이 만들어진다.
		//방금 업로드한 파일명 알아맞추기
		String ori = multi.getOriginalFileName("photo");
		String ext = FileManager.getExtend(ori);
		out.print("당신이 업로드한 로컬 원해 파일명은? "+ori);

		String filename = time+"."+ext;
		
		out.print("내가 조작한 파일명은 "+filename);
		
		//조작한 이름으로 파일명을 바꾸어야 함
		//결국 파일을 다루어야 하므로 javaSE의 File클래스를 이용하면 된다!!
		//File 클래스의 api문서를 찾아서 파일명을 바꾸는 메서드를 찾아보자
		File savedFile =multi.getFile("photo");
		savedFile.renameTo(new File(saveDirectory+"/"+filename));
		
		//클라이언트에게 전송할 응답정보를 가진 객체,
		//클라이언트의 브라우저로 하여금, 지정한 URL로 시도하게 만듦 
		//response.sendRedirect("/gallery/photo_list.jsp");
		out.print("업로드 완료");
	}catch(IOException e){
		e.printStackTrace();//콘솔 로그에 에러 출력, 관리자에게 이메일, sms 보내기...
		out.print("업로드 용량이 너무 큽니다!");//서블릿 쓰레드 에러...(servlet 클래스를 다뤄야 함..)
	}
	//업로드 컴포넌트를 이용할 경우, 스트링 파라미터도 업로드 컴포넌트를 이용해야 한다.
	
	
%>








