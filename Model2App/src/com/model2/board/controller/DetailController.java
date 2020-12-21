package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.board.model.BoardDAO;
import com.model2.comment.model.CommentDAO;
import com.model2.domain.Board;

public class DetailController implements Controller{
	BoardDAO dao = new BoardDAO();
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id =Integer.parseInt(request.getParameter("board_id")); 
		Board board = dao.select(board_id);
		
		request.setAttribute("board", board);
	}

	@Override
	public String getResultView() {
		return "/result/board/detail";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
