package com.springmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	BoardDAO dao = new BoardDAO();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: 로직 객체에 일시킨다.
		List boardList = dao.selectAll();
		
		//4단계: 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		
		mav.setViewName("board/list");
		
		return mav;
	}

}
