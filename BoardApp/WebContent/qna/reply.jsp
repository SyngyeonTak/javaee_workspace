<%@page import="board.model.QnA"%>
<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>

<%
	request.setCharacterEncoding("utf-8");

	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	//넘겨받은 파라미터 값을 이용하여 답글 달자!!
	int team = Integer.parseInt(request.getParameter("team"));//내본글 team
	int rank = Integer.parseInt(request.getParameter("rank"));//내본글 rank
	int depth = Integer.parseInt(request.getParameter("depth"));//내본글 depth
	
	//답글을 달기 위한 쿼리문을 알아야 한다!!
	
	//DAO에서 수행할 거지만, 일단 이해를 위해 여기에 적겠다.
	
	//1단계: 후발로 등록된 글이 들어갈 자리 확보(기존 글들을 밀어내는 효과)
	//String  sql = "update qna set rank = rank+1 where team = "+team+" and where rank > "+rank;
	//out.print(sql);
	
	//out.print("<br>");
	
	//2단계: 내가 본글의 바로 아래쪽에 답변 insert
	//sql = "insert into qna(team, rank, depth) values("+team+", "+(rank+1)+", "+(depth+1)+")";
	//out.print(sql);
	
	QnADAO dao = new QnADAO();
	QnA qna = new QnA();
	qna.setWriter(writer);
	qna.setTitle(title);
	qna.setContent(content);
	
	qna.setTeam(team);
	qna.setRank(rank);
	qna.setDepth(depth);
	
	int result = dao.reply(qna);
	

	if(result == 0){
		out.print(getMsgBack("답글 등록 실패"));
	}else{
		out.print(getMsgURL("답글 등록 성공", "/qna/list.jsp"));		
	}
%>









