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
		
		//����ѷ��� Ŭ���̾�Ʈ�� ���Ե� �޽����� ó���ؾ� �Ѵ�?? �ƴϴ�??
		HttpSession session = request.getSession();
		if(result == 0) {
			//���� �������� �����ش�. �ᱹ ������ ���� �ȳ��� �����ִ� �������� ������ �ΰ�, �� �������� �����ش�.
			session.setAttribute("msg", "�������� �ʾҽ��ϴ�.(�����ڵ� 300)");
			response.sendRedirect("/error/message.jsp");
		}else {
			response.sendRedirect("/board/list");
						
		}
		
	}
}