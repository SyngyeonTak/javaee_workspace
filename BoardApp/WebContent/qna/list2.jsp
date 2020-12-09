<%@page import="board.model.QnA"%>
<%@page import="java.util.List"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	QnADAO dao = new QnADAO();
	List<QnA> list = dao.selectAll();

	int totalRecord = list.size();//총 레코드 수, 실제 DB에 있는 데이터 수를 대입하면 된다!!
	int pageSize = 10;//한 페이지 당 몇건씩 보여질 레코드 수
	int totalPage =  (int)(Math.ceil((float)totalRecord / pageSize));//총 페이지수
	int blockSize = 10;//한 블럭당 보여질 페이지 수
	
	int currentPage =1;//현재 페이지
	if(request.getParameter("currentPage")!=null){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));//parseInt할 값이 null이면 안된다.		
	}
	/*
	내거
	int firstPage =(int)(Math.ceil((float)currentPage/blockSize))*blockSize-(blockSize-1);//반복문의 시작 값
	int lastPage = (int)(Math.ceil((float)currentPage/blockSize))*blockSize;//반복문의 끝값
	*/
	
	int firstPage = currentPage - (currentPage-1)%blockSize;
	int lastPage = firstPage + (blockSize-1);
	int num = totalRecord -  (currentPage-1)*pageSize;
	int curPos = (currentPage-1)*pageSize;//페이지당 List에서의 시작 index
%>
<%="totalRecord는 "+ totalRecord+"<br>"%>
<%="pageSize는 "+ pageSize+"<br>"%>
<%="totalPage는 "+ totalPage+"<br>"%>
<%="blockSize는 "+ blockSize+"<br>"%>
<%="currentPage는 "+ currentPage+"<br>"%>
<%="firstPage는 "+ firstPage+"<br>"%>
<%="lastPage는 "+ lastPage+"<br>"%>
<%="num는 "+ num+"<br>"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
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
<body>
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  <%for(int i =1; i<=pageSize; i++){ %>
  	<%if(num< 1) break; %>
  	<% QnA qna = list.get(curPos++);//break문을 만나지 않았다는 것은 레코드가 있다는 것이므로 break문 아래에서 데이터를 추출하자 %>
  <tr>
    <td><%=num-- %></td>
    <td>
    	<%if(qna.getDepth()>0){ %>
    		<img src ="/images/reply.png" style="margin-left:<%=10*qna.getDepth()%>" width="15px">
    	<%} %>
    	<%=qna.getTitle() %>
    </td>
    <td><%=qna.getWriter() %></td>
    <td><%=qna.getRegdate() %></td>
    <td><%=qna.getHit() %></td>
   
    
  </tr>
<%} %>
  <tr>
	<td colspan = "5" style="text-align:center">
	
		<%if((firstPage-1)>0){ %>
		<a href="/qna/list2.jsp?currentPage=<%=firstPage-1 %>">◀</a>		
		<%}else{ %>
			<a href="javascript:alert('처음페이지입니다.')">◀</a>
		<%} %>
		<%for(int i=firstPage; i<=lastPage; i++){ %>
			<%if(i > totalPage) break;//페이지를 출력하는 i가 총페이지 수에 도달하면 반복문 빠져 나와라...%>
		<a href = "/qna/list2.jsp?currentPage=<%=i%>"<%if(currentPage==i) {%>class="pageNum"<%} %>>[<%=i %>]</a>
	
		<%} %>
		<%if(lastPage<=(totalRecord/pageSize)){ %>
		<a href="/qna/list2.jsp?currentPage=<%=lastPage+1 %>">▶</a>				
		<%}else{ %>
			<a href="javascript:alert('마지막 페이지입니다.')">▶</a>
		<%} %>
	</td>
	</tr>
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