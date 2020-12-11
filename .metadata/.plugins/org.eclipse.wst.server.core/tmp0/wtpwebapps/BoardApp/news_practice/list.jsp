<%@page import="board.model.News"%>
<%@page import="java.util.List"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	NewsDAO dao = new NewsDAO();

	List<News> list = dao.selectAll();

	int totalRecord = list.size();
	int pageSize = 10;
	int currentPage = 1;
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));	
	}
	
	int totalPage = (int)Math.ceil((float)totalRecord/pageSize);
	
	int blockSize = 10;
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage+ (blockSize-1);
	int curPos = (currentPage-1)*pageSize;
	int num = totalRecord - curPos;
	
	
	out.print("firstPage"+firstPage+"<br>");
	out.print("lastPage"+lastPage+"<br>");
	out.print("totalRecord"+totalRecord+"<br>");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>

table{
	width: 100%;
	border: 1px solid #cccccc;
}
td{
	border: 1px solid #cccccc;
}
a{
 	text-decoration : none;
}
.pageNum{
	font-size:20px;
	color: bluc;
	font-weight: bold;
}
.inactive{
	color:#cccccc;
}
</style>
<script>
	function showColor(obj){
		obj.style.background = "dodgerblue";
	}
	
	
	function hideColor(obj){
		obj.style.background = "";
		
	}

</script>
</head>
<body>
	<table>
		<tr>
			<td width = "5%">No</td>
			<td width = "70%">제목</td>
			<td width = "10%">작성자</td>
			<td width = "10%">등록일</td>
			<td width = "5%">조회수</td>
		</tr>
		<%for(int i = 1; i<=pageSize; i++){ %>
			<%if(num < 1) break; %>
			<%News news = list.get(curPos++); %>
			<tr  onMouseOver = "showColor(this)" onMouseOut = "hideColor(this)">
				<td><%=num-- %></td>
				<%if(news.getWriter().length() <1){ %>
					<td><span class = "inactive"><%=news.getTitle() %></span></td>
				<%}else{ %>
					<td><a href = "detail.jsp?news_id=<%=news.getNews_id() %>"><%=news.getTitle() %><%if(news.getCnt() >0) {%>[<%=news.getCnt() %>] <%} %></a></td>			
				<%} %>
				
				<td><%=news.getWriter() %></td>
				<td><%=news.getRegdate().substring(0, 10) %></td>
				<td><%=news.getHit() %></td>
			</tr>
		<%} %>
		<tr>
		
			<td colspan = "5" style = "text-align:center;">
				
				<%if(firstPage >1){ %>
					<a href = "list.jsp?currentPage=<%=firstPage-1 %>">◀</a>				
				<%}else{ %>
					<a href = "javascript:alert('처음페이지 입니다.')">◀</a>								
				<%} %>
		
				<%for(int i=firstPage; i<=lastPage; i++){ %>
					<%if(i > totalPage) break; %>
					<a href = "list.jsp?currentPage=<%=i %>" <%if(currentPage == i){ %> class = "pageNum" <%} %>>[<%=i %>]</a>
				<%} %>
				
				<%if(lastPage <= totalPage){ %>
					<a href = "list.jsp?currentPage=<%=lastPage+1 %>">▶</a>				
				<%}else{ %>
					<a href = "javascript:alert('마지막 페이지 입니다.')">▶</a>								
				<%} %>
				
			<td>
		</tr>
		<tr>
			<td colspan="5" onclick = "location.href = 'regist_form.jsp'">
				<button>글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>








