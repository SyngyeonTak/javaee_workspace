/*
 * 댓글 등록요청을 처리하는 컨트롤러
 * */
package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.Controller;
import com.model2.comment.model.CommentDAO;
import com.model2.domain.Comment;

public class RegistController implements Controller{
	CommentDAO dao = new CommentDAO();
	Comment comment = new Comment(); 
	//댓글의 등록은 비동기 요청으로 들어오기 때문에, 응답 정보는 페이지를 보여주는 게 아니라,
	//데이터를 전송해야 함!!
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		String msg = request.getParameter("msg");
		String author= request.getParameter("author");
		
		comment = new Comment();
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(board_id);
		int result = dao.insert(comment);
		
		request.setAttribute("result", result);
	}

	@Override
	public String getResultView() {
		return "/result/comment/regist";
	}

	@Override
	public boolean isForward() {
		return true;
	}

}
