package com.webapp1216.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp1216.board.model.Notice;
import com.webapp1216.board.model.NoticeDAO;

public class EditServlet extends HttpServlet{
	NoticeDAO dao = new NoticeDAO();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		String title = request.getParameter("title");
		String writer= request.getParameter("writer");
		String content = request.getParameter("content");
		
		Notice notice = new Notice();
		
		notice.setNotice_id(notice_id);
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = dao.update(notice);
		
		HttpSession session = request.getSession();
		if(result == 0) {
			session.setAttribute("msg", "���� �����Դϴ�.");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/detail?notice_id="+notice_id);
			
		}

	}
}




