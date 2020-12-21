package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.comment.model.CommentDAO;

/*
 * 댓글 목록 요청을 처리하는 컨트롤러
 * 댓글 목록은 클라이언트에게 페이지 재접속을 유도하면 안되며, 클라이언트가 받아서 화면에 출력할 데이터만 
 * 보내야 한다.
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
