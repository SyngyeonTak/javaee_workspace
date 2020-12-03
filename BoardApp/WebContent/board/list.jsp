<%@page import="board.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%

	NoticeDAO noticeDAO = new NoticeDAO();
	ArrayList<Notice> noticeArray = noticeDAO.selectAll();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
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
</style>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").on("click", function(){
			location.href="/board/regist_form.jsp";
		});
	});//onload
</script>
</head>
</head>
<body>

<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  	<%for(int i = 0; i< noticeArray.size(); i++){%>
  <tr><% Notice notice = noticeArray.get(i); %>
    <td><%=notice.getNotice_id()%></td>
    <td>
		<a href = "/board/detail_form.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle()%></a>
	</td>
    <td><%=notice.getAuthor()%></td>
    <td><%=notice.getRegdate()%></td>
    <td><%=notice.getHit()%></td>
  </tr>
  <tr>
  <%}%>
	<td colspan = "5">
		<button>글 등록</button>
	</td>
  </tr>
  <tr>
	<td colspan = "5" style="text-align:center">
		<%@ include file="/inc/footer.jsp"%>
	</td>
  </tr>
</table>

</body>
</html>