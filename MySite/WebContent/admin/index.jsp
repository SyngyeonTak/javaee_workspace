<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	Admin admin = (Admin)request.getAttribute("admin");
	String mid = admin.getMid();
	String password = admin.getPassword();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/admin/inc/head.jsp"%>
</head>
<body>
<div><%=mid%>님 로그인 중입니다.</div>
<%@ include file="/admin/inc/topnavi.jsp" %>

	<div style="padding-left: 16px">
		<h2>Top Navigation Example</h2>
		<p>Some content..</p>
	</div>

</body>
</html>
