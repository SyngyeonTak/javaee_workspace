<%@page import="java.util.List"%>
<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@page import="board.model.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="db.DBManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%

	QnADAO dao= new QnADAO();
	List<QnA> list = dao.selectAll();
	int totalRecord = list.size();
	int pageSize = 10;
	int totalPage = (int)(Math.ceil((float)totalRecord/pageSize));
	
	int currentPage = 1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	int blockSize = 10;
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage + (blockSize-1);
	int num = totalRecord-(currentPage-1)*pageSize;
	int curPos =  (currentPage-1)*pageSize;
	
	out.print("totalRecord는 "+totalRecord+"<br>");
	out.print("currentPage는 "+currentPage+"<br>");
	out.print("pageSize는 "+pageSize+"<br>");
	out.print("totalPage는 "+totalPage+"<br>");
	out.print("firstPage는 "+firstPage+"<br>");
	out.print("lastPage는 "+lastPage+"<br>");
	out.print("num는 "+num+"<br>");
	out.print("curPos는 "+curPos+"<br>");
	
	
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
img{
	box-sizing:border-box;
}
a{
	text-decoration: none;
}
.pageNum{
	font-size:20pt;
	color:red;
	font-weight:bold;	
}
</style>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(function(){
		$("button").on("click", function(){
			location.href="/qna/regist_form.jsp";
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
  	<%for(int i = 0; i< pageSize; i++){%>
  	<% if(curPos >= list.size()) break; %>
  	<% QnA qna = list.get(curPos++);%>
  <tr>
    <td><%=num--%></td>
    <td>
    	<%if(qna.getDepth() > 0){ %>
    		<img src ="/images/reply.png" style = "margin-left: <%=10*qna.getDepth() %>px" width="15px">
    	<%} %>
    	<a href = "/qna/detail_form.jsp?qna_id=<%=qna.getQna_id()%>"><%=qna.getTitle() %></a>
    </td>
    <td><%=qna.getWriter() %></td>
    <td><%=qna.getRegdate().substring(0, 10) %></td>
    <td><%=qna.getHit() %></td>
  </tr>
  <%}%>

  <tr> 
	<td colspan = "5" style = "text-align:center">
	
		<%if(firstPage > 1){ %>
			<a href = "/qna/list.jsp?currentPage=<%=firstPage-1 %>" >◀</a>		
		<%}else{ %>
			<a href ="javascript:alert('처음 페이지입니다.')">◀</a>
		<%} %>
		
		<%for(int i =firstPage; i<=lastPage;i++){ %>
			<%if(i >totalPage) break; %>
			<a href = "/qna/list.jsp?currentPage=<%=i %>" <%if(i == currentPage) {%>class = "pageNum"<%} %>>[<%=i %>]</a>
		<%} %>
		
		<%if(lastPage < totalPage ){ %>
			<a href = "/qna/list.jsp?currentPage=<%=lastPage+1 %>" >▶</a>					
		<%}else{ %>
			<a href ="javascript:alert('마지막 페이지입니다.')">▶</a>
		<%} %>
		
	</td>
  </tr>
  <tr> 
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