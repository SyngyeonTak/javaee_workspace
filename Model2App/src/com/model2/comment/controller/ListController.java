package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.comment.model.CommentDAO;

/*
 * ��� ��� ��û�� ó���ϴ� ��Ʈ�ѷ�
 * ��� ����� Ŭ���̾�Ʈ���� ������ �������� �����ϸ� �ȵǸ�, Ŭ���̾�Ʈ�� �޾Ƽ� ȭ�鿡 ����� �����͸� 
 * ������ �Ѵ�.
 * */

public class ListController implements Controller{
	CommentDAO dao = new CommentDAO();
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		List list = dao.selectAll(board_id);
		
		request.setAttribute("commentList", list);
	}

	@Override
	public String getResultView() {
		return "/result/comment/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
