package com.webapp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;

public class DetailServlet extends HttpServlet{
	//get(목록보기, 상세보기) or post(등록, 수정요청)
	NoticeDAO dao = new NoticeDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		
		Notice notice = dao.select(notice_id);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("notice", notice);//세션에 담아두기!!
		//클라이언트가 재접속을 해도, 세션에 담아놓으면 참조가 가능하다
		response.sendRedirect("/board/detail.jsp");
	}
}








