<%@ page contentType="text/html;charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
function send(){
	var form = document.querySelector("form");
	form.action="/movie.do";
	form.method = "post"
	form.submit();
}

</script>
</head>
<body>
	<form>
		<select name = movie>
			<option>영화를 선택하세요</option>
			<option value="쇼생크탈출">쇼생크탈출</option>
			<option value="대부">대부</option>
			<option value="해리포터">해리포터</option>
			<option value="나홀로집에">나홀로집에</option>
		</select>
		<button type = "button" onClick="send()">평점보기</button>
	</form>
	
</body>
</html>














