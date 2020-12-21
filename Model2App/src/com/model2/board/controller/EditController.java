package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.board.model.BoardDAO;
import com.model2.domain.Board;

public class EditController implements Controller{
	BoardDAO dao = new BoardDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		Board board= new Board();
		board.setBoard_id(board_id);
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		//3단계 수정처리
		int result = dao.update(board);
		
		//4단계: 수정 후 상세페이지에서, 수정된 내용을 봐야하기에 저장할 것이 있다.
		request.setAttribute("result", result);
		request.setAttribute("board", board);
	}

	@Override
	public String getResultView() {
		return "/result/board/edit";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
