<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>
<body>
고양이가 발급한 세션 아이디는
<%
/*
jsp 내장객체
-request, response, out, application, session
*/
String id = session.getId();//tomcat이 발급한 id
										//브라우저를 끄지 않는 이상 계속 기억한다.
out.print(id);
%>
</body>
</html>








