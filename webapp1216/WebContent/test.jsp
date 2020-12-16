<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	//데이터베이스와 연동되는지 확인
	
	InitialContext context = new InitialContext();//jndi 검색 객체
	DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");//톰캣 서버에 설정한 리소스를 찾자
	
	//찾아낸 커넥션풀을 이용하여 커넥션한 개를 꺼내고 주소값이 나오면 성공
	Connection con = (Connection)ds.getConnection();
	out.print(con);
	
%>