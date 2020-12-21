
package com.model2.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	BoardDAO dao = new BoardDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//3단계: 알맞는 로직 객체에 일 시키기
		List list = dao.selectAll();
		
		request.setAttribute("boardList", list);
	}

	@Override
	public String getResultView() {
		
		return "/result/board/list";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
