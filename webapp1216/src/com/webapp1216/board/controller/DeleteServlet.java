package com.webapp1216.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.NoticeDAO;

public class DeleteServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		
		int result = dao.delete(notice_id);
		
		//컨드롤러가 클라이언트가 보게될 메시지를 처리해야 한다?? 아니다??
		HttpSession session = request.getSession();
		if(result == 0) {
			//실패 페이지를 보여준다. 결국 에러에 대한 안내를 보여주는 페이지를 별도로 두고, 그 페이지를 보여준다.
			session.setAttribute("msg", "삭제되지 않았습니다.(에러코드 300)");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/list");
						
		}
		
	}
}
