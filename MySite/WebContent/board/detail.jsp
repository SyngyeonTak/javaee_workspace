<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="board.model.Board"%>
<%@page import="common.db.PoolManager"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
	int board_id = Integer.parseInt(request.getParameter("board_id"));
	MybatisBoardDAO dao = new MybatisBoardDAO();
	Board board = dao.select(board_id);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #4CAF50;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	var bt_edit = $("input[type='button']")[0];//수정
	var bt_del = $("input[type='button']")[1];//삭제
	var bt_list = $("input[type='button']")[2];
	
	$(bt_edit).click(function(){
		edit();
		
	});
	
	$(bt_del).click(function(){
		del()
	});
	
});

function edit(){
	$("form").attr({
		method : "post",
		enctype : "multipart/form-data",
		action : "edit.jsp"
	});
	$("form").submit();
}

function del(){
	$("form").attr({
		method : "post",
		action : "delete.jsp"
	});
	$("form").submit();
}
</script>
</head>
<body>

<h3>글 상세 보기</h3>

<div class="container">
  <form>
    <input type="hidden" name="board_id" value = "<%=board.getBoard_id()%>">
    <input type="text" name="title" value = "<%=board.getTitle()%>">
	<input type="text" name="writer" value = "<%=board.getWriter()%>">
    <textarea name="content" style="height:200px"><%=board.getContent() %></textarea>

    <input type="hidden" name="filename" value = "<%=board.getFilename()%>">
    <input type="file" name="photo"><p>
    <input type="button" value="수정">
    <input type="button" value="삭제">
    <input type="button" value="목록보기" onClick = "location.href = 'list.jsp'">
  </form>
</div>

</body>
</html>
