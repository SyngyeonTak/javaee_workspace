<%@page import="java.util.List"%>
<%@page import="board.model.Comments"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.model.CommentsDAO"%>
<%@page import="board.model.News"%>
<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	String news_id = request.getParameter("news_id");//파라미터 받기
	NewsDAO dao = new NewsDAO();
	News news = dao.select(Integer.parseInt(news_id));//데이터 한 건 가져오기!
	
	//이 게시물에 딸려있는 모든 코멘트 게시물 가져오기!!
	CommentsDAO commentDAO = new CommentsDAO();
	List<Comments> list = commentDAO.selectAll(Integer.parseInt(news_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
table {
	width: 70%;
}

td {
	border: 2px solid #cccccc;
}

td input, textarea {
	width: 97%;
	font-size: 1em;
}

textarea {
	height: 150px;
}
input[name ='msg']{
	width:65%;
}

input[name ='author']{
	width:15%;
}

p{
	display: inline-block;
}

.msg{
	width:65%
}
.author{
	width:10%
}
.cdate{
	width:13%
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var author;
	var msg;
	var cdate;

	$(function() {
		$($("button")[0]).click(function() {//수정
			if(confirm("수정하시겠습니까?")){
				update();
			}
		});

		$($("button")[1]).click(function() {//삭제
			if(confirm("삭제하시겠습니까?")){
				del();
			}
		});
		
		$($("button")[2]).click(function() {//목록
			location.href = "list.jsp";
		});
		
		$($("button")[3]).click(function() {//댓글 등록
			reply();
		});
		
		$($("button")[4]).click(function() {//비동기 댓글 등록
			asyncReply();
		});
	
		asyncList();
	});
	
	function getList(data){
		var commentBox = document.getElementById("commentBox");
		commentBox.innerHTML = "";
		var json = JSON.parse(data);
		var jsonArray = json.commentsList;
		tag = "";
		for (var i = 0; i < jsonArray.length; i++) {
			var comments = jsonArray[i];
			tag += "<div>";
			tag += "<p class = \"msg\">"+comments.msg+"</p>"
			tag += "<p class = \"author\">"+comments.author+"</p>"
			tag += "<p class = \"cdate\">"+comments.cdate+"</p>"
			tag += "<button type = \"button\" onClick = \"asyncDelete('"+comments.comments_id+"')\">삭제</button>"
			tag += "</div>";	
		}
		commentBox.innerHTML = tag;
		
	}
	
	function asyncDelete(comments_id){
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		    	if(this.responseText = 0){
		    		alert("삭제 실패");
		    	}else{
				    asyncList();
		    	}
		    }
		  };

		  xhttp.open("get", "asyncdelete.jsp?comments_id="+comments_id, true);
		  xhttp.send();
	}
	
	function asyncReply(){
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		     getList(this.responseText);
		    }
		  };
		  var author = $("input[name='author']").val();
		  var msg = $("input[name='msg']").val();
		  console.log(author);
		  console.log(msg);
		  
		  
		  var params = "news_id=<%=news_id%>&author="+author+"&msg="+msg;
		  xhttp.open("post", "asyncreply.jsp", true);
		  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  xhttp.send(params);
	}
	
	function asyncList(){
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (this.readyState == 4 && this.status == 200) {
		     getList(this.responseText);
		    }
		  };
		  xhttp.open("get", "asynclist.jsp?news_id=<%=news_id%>", true);
		  xhttp.send();
	}
	
	function update(){
		$("form").attr({
			method : "post",
			action: "update.jsp"
		});
		$("form").submit();		
	
	}
	
	function del(){
		<%if(list.size() > 0){%>
			//자식 코멘트가 존재한다면, 업데이트!!!
			$("form").attr({
				method : "post",
				action: "replace.jsp"
			});
			$("form").submit();		
		
		<%}else{%>
			//자식 코멘트가 없다면 그냥 삭제
			$("form").attr({
				method : "post",
				action: "delete.jsp"
			});
			$("form").submit();			
			
		<%}%>
	}
	
	//서버에 댓글 등록 요청하기!!
	function reply(){
		$("form").attr({
			method : "post",
			action: "reply.jsp"
		});
		$("form").submit();
	}
	
	function regist() {
		$("form").attr({
			method : "post",
			action: "regist.jsp"
			
		});
		$("form").submit();
	}
</script>
</head>
<body>
	<form>
		<input type="hidden" name="news_id" value = "<%=news.getNews_id() %>">
		<table align="center">
			<tr>
				<td><input type="text" name="writer" value = "<%=news.getWriter() %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" value = "<%=news.getTitle() %>"></td>
			</tr>
			<tr>
				<td><textarea name="content"><%=news.getContent() %></textarea></td>
			</tr>
			<tr>
				<td align="center">
					<button type = "button">수정</button>
					<button type = "button">삭제</button>
					<button type = "button">목록</button>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<input type = "text" placeholder = "댓글을 입력"  name = "msg">
						<input type = "text" placeholder = "작성자 입력"  name = "author">
						<button type = "button">등록</button>
						<button type = "button">비동기 등록</button>
					</div>
				</td>
			</tr>
			<!-- 댓글 리스트 영역 -->
				<tr >
					<td id ="commentBox">	</td>
				</tr>
		</table>
	</form>
</body>
</html>




