<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%  Admin admin = (Admin)session.getAttribute("ad"); %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="/admin/inc/head.jsp"%>
<style>
body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
.topnav {
	overflow: hidden;
	background-color: #333;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>
<div>
	<%=admin.getMid()%>님 로그인 중입니다.
	<a href = "/admin/logout.jsp">로그아웃</a>
</div>
<%@ include file="/admin/inc/topnavi.jsp" %>

<h2>게시판 목록</h2>
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
  </tr>
 
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Points</th>
  </tr>
 
</table>

</body>
</html>
