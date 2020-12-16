/*
 * jsp나 servlet 둘 다 웹서버에서 해석 및 실행되어지므로, 글등록 요청을 처리할 때는
 * 둘다 가능하다. 하지만 현재시점에서 jsp로 개발하지 않는 이유는??'
 * 앞으로 jsp는 서블릿의 디자인적 처리 시 단점을 보완하기 위해 개발된 기술이므로
 * jsp는 주로 디자인 영역에서 사용된다..
 * */

package com.webapp1216.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;

public class RegistServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	
	/*doxxx형 메서드는 service()메서드에 의해 호출된다, 이때 요청, 응답 객체가 전달..*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*클라이언트의 파라미터받기*/
		request.setCharacterEncoding("utf-8");//파라미터에 대한 인코딩 처리
		//response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		String title = request.getParameter("title");
		String writer= request.getParameter("writer");
		String content = request.getParameter("content");
		//빈즈 태그 사용가능?? jsp에서만 가능함
		
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		//클라이언트의 브라우저에 출력할 데이터를 응답 객체 심어 놓기
		//out.print("제목은 "+title+"<br>");
		//out.print("작성자는 "+writer+"<br>");
		//out.print("글 내용"+content+"<br>");
		
		int result = dao.insert(notice);
		
		if(result == 0) {
			out.print("<script>");
			out.print("alert('등록실패');");
			out.print("history.back();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('등록성공');");
			out.print("location.href='/board/list';");
			out.print("</script>");			
		}
		
	}
}






