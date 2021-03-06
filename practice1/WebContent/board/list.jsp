<%@page import="common.board.Pager"%>
<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="board.model.Board"%>
<%@ page contentType="text/html;charset=utf-8"%>

<%
	MybatisBoardDAO dao = new MybatisBoardDAO();
	List<Board> list = dao.selectAll();
	
	Pager pager = new Pager();
	pager.init(request, list);//페이지 처리에 대한 계산!!
	//쓰는 톰캣이 내부 톰캣이라 이클립스 내부 데이터에 쌓이다.	 내부 톰캣은 시뮬레이션이다.
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
a{
 	text-decoration: none;
}

.pageNum{
	font-size : 20px;
	font-weight : bold;
	color : red;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th>No</th>
			<th>이미지</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%
			int num=pager.getNum();
			int curPos=pager.getCurPos();
		%>
		<%for(int i = 0; i<pager.getPageSize(); i++) {%>
			<%if(num <1) break; %>
			<% Board board = list.get(curPos++); %>
			<tr>
				<td><%=num-- %></td>
				<td><img src = "/data/<%=board.getFilename() %>" width = "15px"/></td>
				<td><a href="/board/detail.jsp?board_id=<%=board.getBoard_id()%>"><%=board.getTitle() %></a></td>
				<td><%=board.getWriter() %></td>
				<td><%=board.getRegdate() %></td>
				<td><%=board.getHit() %></td>
			</tr>
		<%} %>
		<tr>
			<td colspan="6" style = "text-align:center;">
				<%if(pager.getFirstPage() > 1) {%>
					<a href ="list.jsp?currentPage=<%=pager.getFirstPage()-1%>">◀</a>
				<%}else{ %>
					<a href ="javascript:alert('처음 페이지 입니다.')">◀</a>
				<%} %>
			
				<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
					<%if(i > pager.getTotalPage()) break; %>
					<a href = "list.jsp?currentPage=<%=i%>" <%if(i == pager.getCurrentPage()){ %>class = "pageNum" <%} %>>[<%=i%>]</a>
				<%} %>
				
				<%if(pager.getLastPage() < pager.getTotalPage()) {%>
					<a href ="list.jsp?currentPage=<%=pager.getLastPage()+1%>">▶</a>
				<%}else{ %>
					<a href ="javascript:alert('마지막 페이지 입니다.')">▶</a>
				<%} %>
				
			</td>
		</tr>
		<tr>
			<td colspan="6">
				<button onclick = "location.href = 'regist_form.jsp'">글 쓰기</button>
			</td>
		</tr>
	</table>

</body>
</html>
