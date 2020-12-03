<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ include file="/inc/lib.jsp"%>
<%
	//클라이언트가 전송한 파라미터를 받아서 mysql에 넣을 예정임
	//별도의 디자인 코드는 필요하지 않다.
	out.print("이 페이지에서 클라이언트가 전송한 파라미터들을 데이터베이스에 넣을 예정\n");
	
	//파라미터가 영문이 아닌 경우 깨진다. 따라서 파라미터를 대상으로 한 인코딩을 지정하면 된다.
	//주의!! 파라미터를 받기 전에 미리 세팅해야 한다.
	request.setCharacterEncoding("utf-8");//한국어, 중국어, 아랍어, 전세계 모든 언어가
																//깨지지 않음

	//jsp가 지원하는 내장객체 중, request객체를 이용하여 클라이언트의 전송 파라미터를 받아보자!!
	String author = request.getParameter("author");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	//mysql insert 진행한다!!
	//드라이버 로드, 접속, 쿼리, 닫기
	String driver = "org.mariadb.jdbc.Driver";
	String URL_mioDB  = "jdbc:mariadb://localhost:3306/db1202";
	String user = "root";
	String password ="1234";
	
	Class.forName(driver);
	Connection con = DriverManager.getConnection(URL_mioDB, user, password);

	if(con == null){
		out.print(getMsgBack("접속 실패"));
	}else{
		out.print("<script>");
		out.print("alert('접속 성공')");
		out.print("</script>");
		String sql = "insert into notice(title, author, content) values(?, ?, ?)";
		PreparedStatement pstmt = null;
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, author);
		pstmt.setString(3, content);

		int result = pstmt.executeUpdate();

		if(result != 1){
			out.print(getMsgBack("글 등록 실패"));
		}else{
			out.print(getMsgURL("글 등록 성공", "/board/list.jsp"));
		}

		if(pstmt != null) pstmt.close();
		if(con != null) con.close();
	}

%>